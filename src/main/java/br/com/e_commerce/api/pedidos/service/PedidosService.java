    package br.com.e_commerce.api.pedidos.service;


    import br.com.e_commerce.api.exeption.BadRequest;
    import br.com.e_commerce.api.exeption.NotFound;
    import br.com.e_commerce.api.exeption.PagamentoException;
    import br.com.e_commerce.api.itemPedido.entity.ItemPedido;
    import br.com.e_commerce.api.pedidos.dto.CriarPedidoRequest;
    import br.com.e_commerce.api.pedidos.dto.MercadoPagoPixResponse;
    import br.com.e_commerce.api.pedidos.dto.PedidoResponse;
    import br.com.e_commerce.api.pedidos.entity.Pedidos;
    import br.com.e_commerce.api.pedidos.enums.Status;
    import br.com.e_commerce.api.pedidos.mapper.PedidosMapper;
    import br.com.e_commerce.api.pedidos.repository.PedidosRepository;
    import br.com.e_commerce.api.produtos.entity.Produtos;
    import br.com.e_commerce.api.produtos.repository.ProdutosRepository;
    import br.com.e_commerce.api.user.entity.Usuarios;
    import br.com.e_commerce.api.user.repository.UsuariosRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import java.util.LinkedHashMap;
    import java.util.List;
    import java.util.Map;

    import static br.com.e_commerce.api.pedidos.enums.MercadoPagoStatus.*;

    @Service
    public class PedidosService {

        private final PedidosRepository pedidosRepository;
        private final ProdutosRepository produtosRepository;
        private final MercadoPagoService mercadoPagoService;
        private final PedidosMapper pedidosMapper;
        private final UsuariosRepository usuariosRepository;


        public PedidosService(PedidosRepository pedidosRepository, ProdutosRepository produtosRepository, MercadoPagoService mercadoPagoService, PedidosMapper pedidosMapper,UsuariosRepository usuariosRepository) {
            this.pedidosRepository = pedidosRepository;
            this.produtosRepository = produtosRepository;
            this.mercadoPagoService = mercadoPagoService;
            this.pedidosMapper = pedidosMapper;
            this.usuariosRepository = usuariosRepository;
        }

        public List<PedidoResponse> mostrarPedidos() {
           return pedidosRepository.findAll().stream().map(pedidos -> pedidosMapper.toDTO(pedidos)).toList();
        }


        @Transactional
        public PedidoResponse criarPedido(Long usuarioId, CriarPedidoRequest request) {

            Usuarios usuario = usuariosRepository.findById(usuarioId)
                    .orElseThrow(() -> new NotFound());

            Map<Long, Integer> quantidadePorProduto = new LinkedHashMap<>();

            for (CriarPedidoRequest.ItemPedidoRequest item : request.itens()) {
                quantidadePorProduto.merge(item.produtoId(), item.quantidade(), Integer::sum);
            }

            Pedidos pedido = new Pedidos();
            pedido.setUsuarios(usuario);

            BigDecimal valorTotal = BigDecimal.ZERO;


            for (Map.Entry<Long, Integer> entry : quantidadePorProduto.entrySet()) {

                Produtos produto = produtosRepository.findById(entry.getKey())
                        .orElseThrow(() -> new NotFound());

                int quantidade = entry.getValue();

                if (!Boolean.TRUE.equals(produto.getAtivo())) {
                    throw new BadRequest();
                }
                if (produto.getEstoque() < quantidade) {
                    throw new BadRequest();
                }

                ItemPedido item = new ItemPedido();
                item.setPedido(pedido);
                item.setProduto(produto);
                item.setQuantidade(quantidade);
                item.setPrecoUnitario(produto.getPreco());
                pedido.getItens().add(item);

                valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(quantidade)));
            }

            pedido.setValorTotal(valorTotal);

            MercadoPagoPixResponse pix = mercadoPagoService.gerarPagamentoPix(usuario, valorTotal);


            if (pix == null || pix.id() == null) {
                throw new PagamentoException();
            }
                pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);
                pedido.setPagamentoIdGateway(String.valueOf(pix.id()));

                if (pix.pointOfInteraction() != null) {
                    pedido.setQrCode(pix.pointOfInteraction().qrCode());
                }

                return pedidosMapper.toDTO(pedidosRepository.save(pedido));
            }


        @Transactional
        public void processarNotificacaoPagamento(String pagamentoId) {

            MercadoPagoPixResponse pagamento = mercadoPagoService.buscarPagamentoPorId(pagamentoId);
            if (pagamento == null) return;

            Pedidos pedido = pedidosRepository.findByPagamentoIdGateway(pagamentoId)
                    .orElseThrow(() -> new NotFound());


            if (pedido.getStatus() == Status.PAGO) return;

            switch (pagamento.status()) {
                case APPROVED -> {
                    pedido.setStatus(Status.PAGO);
                    pedido.setPagoEm(LocalDateTime.now());

                }
                case REJECTED, CANCELLED -> pedido.setStatus(Status.CANCELADO);
                default -> { }   // pending, in_process...: continua AGUARDANDO_PAGAMENTO
            }
        }

    }

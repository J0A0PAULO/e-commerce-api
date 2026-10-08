package br.com.e_commerce.api.pedidos.service;

import br.com.e_commerce.api.exeption.NotFound;
import br.com.e_commerce.api.itemPedido.entity.ItemPedido;
import br.com.e_commerce.api.pedidos.dto.CriarPedidoRequest;
import br.com.e_commerce.api.pedidos.dto.MercadoPagoPixResponse;
import br.com.e_commerce.api.pedidos.dto.PedidoReponse;
import br.com.e_commerce.api.pedidos.entity.Pedidos;
import br.com.e_commerce.api.pedidos.enums.Status;
import br.com.e_commerce.api.pedidos.mapper.PedidosMapper;
import br.com.e_commerce.api.pedidos.repository.PedidosRepository;
import br.com.e_commerce.api.produtos.entity.Produtos;
import br.com.e_commerce.api.produtos.repository.ProdutosRepository;
import br.com.e_commerce.api.user.entity.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidosService {

    private final PedidosRepository pedidosRepository;
    private final ProdutosRepository produtosRepository;
    private final MercadoPagoService mercadoPagoService;
    private final PedidosMapper pedidosMapper;


    public PedidosService(PedidosRepository pedidosRepository, ProdutosRepository produtosRepository, MercadoPagoService mercadoPagoService, PedidosMapper pedidosMapper) {
        this.pedidosRepository = pedidosRepository;
        this.produtosRepository = produtosRepository;
        this.mercadoPagoService = mercadoPagoService;
        this.pedidosMapper = pedidosMapper;
    }

    public List<PedidoReponse> mostrarPedidos() {
       return pedidosRepository.findAll().stream().map(pedidos -> pedidosMapper.toDTO(pedidos)).toList();
    }


    @Transactional
    public PedidoReponse criarPedido(Usuarios usuarios, CriarPedidoRequest request) {

        Pedidos pedido = new Pedidos();
        pedido.setUsuarios(usuarios);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (CriarPedidoRequest.ItemPedidoRequest itemReq : request.itens()) {

            Produtos produtos = produtosRepository.findById(itemReq.produtoId()).orElseThrow(() -> new NotFound());

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produtos);
            item.setPrecoUnitario(produtos.getPreco());
            item.setQuantidade(itemReq.quantidade());

            pedido.getItens().add(item);

            BigDecimal subTotal = produtos.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            valorTotal = valorTotal.add(subTotal);
        }

        pedido.setValorTotal(valorTotal);

        MercadoPagoPixResponse mercadoPagoPixResponse = mercadoPagoService.gerarPagamentoPix(usuarios, valorTotal);

        if (mercadoPagoPixResponse != null) {

            pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

            pedido.setPagamentoIdGateway(String.valueOf(mercadoPagoPixResponse.id()));

            if (mercadoPagoPixResponse.pointOfInteraction() != null) {

                pedido.setQrCode(mercadoPagoPixResponse.pointOfInteraction().qrCode());

            }

        } else {
            pedido.setStatus(Status.CANCELADO);
        }


        Pedidos save = pedidosRepository.save(pedido);

        return pedidosMapper.toDTO(save);

    }

    @Transactional
    public void processarNotificacaoPagamento(String pagamentoIdGateWay) {

        MercadoPagoPixResponse mercadoPagoPixResponse = mercadoPagoService.buscarPagamentoPorId(pagamentoIdGateWay);

        if (mercadoPagoPixResponse != null && "approved".equalsIgnoreCase(mercadoPagoPixResponse.status().name())) {
            Pedidos pedidos = pedidosRepository.findByPagamentoIdGateway(pagamentoIdGateWay).orElseThrow(() -> new NotFound());
            pedidos.setStatus(Status.PAGO);
            pedidosRepository.save(pedidos);
        }
        ;
    }


}

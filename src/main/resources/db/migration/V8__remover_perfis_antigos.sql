UPDATE usuarios
SET perfil_id = (SELECT id FROM perfis WHERE nome = 'ROLE_ADM')
WHERE perfil_id IN (SELECT id FROM perfis WHERE nome = 'ROLE_ADMIN');

UPDATE usuarios
SET perfil_id = (SELECT id FROM perfis WHERE nome = 'ROLE_CLIENT')
WHERE perfil_id IN (SELECT id FROM perfis WHERE nome = 'ROLE_CLIENTE');

DELETE FROM perfis WHERE nome IN ('ROLE_ADMIN', 'ROLE_CLIENTE');
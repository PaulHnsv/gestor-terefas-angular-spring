-- ─── USUÁRIOS ──────────────────────────────────────────────────────────────────
-- role: 1 = USER | 2 = ADMIN
-- senha admin   → admin123
-- senha paulo   → paulo123
-- senha ana     → ana123
-- senha carlos  → carlos123

INSERT INTO users (username, password, email, role, created_at) VALUES
    ('admin',  '$2b$10$B8dIM/jI40GR/pjmQ.9ky.zpyyOm93vu8zKjctTSs.2J0R4tCnvNa', 'admin@gestor.com',  2, CURRENT_TIMESTAMP),
    ('paulo',  '$2b$10$zAZOZ3gJN3x/SruYp4Jb/OEbQ9PmEinZoDq/iP4acE1G4XpexgiLm', 'paulo@gestor.com',  1, CURRENT_TIMESTAMP),
    ('ana',    '$2b$10$DAkZppeidrngbAmtYqVGLee7gbTzO0lQB2o0LEjHhLhGXusgGWPtS',  'ana@gestor.com',    1, CURRENT_TIMESTAMP),
    ('carlos', '$2b$10$LQlDexX/DYE2z9L5eLqBHOA0GqTowfXw9YdtyYSbIKseTvMqaY7BO', 'carlos@gestor.com', 1, CURRENT_TIMESTAMP);

-- ─── PROJETOS ──────────────────────────────────────────────────────────────────

INSERT INTO projects (name, description, created_at) VALUES
    ('E-commerce',               'Plataforma de vendas online com catálogo, carrinho e checkout.',                                DATEADD('DAY', -60, CURRENT_TIMESTAMP)),
    ('App Mobile de Delivery',   'Aplicativo de entrega de refeições com rastreamento em tempo real.',                            DATEADD('DAY', -45, CURRENT_TIMESTAMP)),
    ('Portal de RH Interno',     'Sistema interno para gestão de férias, ponto eletrônico e folha de pagamento.',                 DATEADD('DAY', -30, CURRENT_TIMESTAMP)),
    ('Refatoração do Legado',    'Migração do sistema monolítico legado para arquitetura de microsserviços.',                     DATEADD('DAY', -20, CURRENT_TIMESTAMP)),
    ('Gateway de Pagamento',     'Integração com provedores de pagamento: Stripe, PayPal e Pix via banco central.',              DATEADD('DAY', -10, CURRENT_TIMESTAMP));

-- ─── TAREFAS ───────────────────────────────────────────────────────────────────
-- status:   1 = PENDENTE | 2 = EM_ANDAMENTO | 3 = CONCLUIDA
-- priority: 1 = BAIXA    | 2 = MEDIA        | 3 = ALTA

-- E-commerce
INSERT INTO tasks (title, description, priority, status, created_at, completed_at, project_id) VALUES
    ('Modelar banco de dados de produtos',      'Definir entidades Product, Category, SKU e seus relacionamentos.',       2, 3, DATEADD('DAY', -58, CURRENT_TIMESTAMP), DATEADD('DAY', -52, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'E-commerce')),
    ('Implementar API de catálogo',             'Endpoints REST para listagem, filtro e detalhe de produtos.',            3, 3, DATEADD('DAY', -55, CURRENT_TIMESTAMP), DATEADD('DAY', -48, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'E-commerce')),
    ('Desenvolver carrinho de compras',         'Lógica de adição, remoção e cálculo de totais com persistência.',        3, 2, DATEADD('DAY', -40, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'E-commerce')),
    ('Integrar sistema de cupons de desconto',  'Suporte a cupons percentuais e de valor fixo com regras de expiração.',  2, 2, DATEADD('DAY', -30, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'E-commerce')),
    ('Implementar busca com Elasticsearch',     'Indexar produtos e criar API de busca full-text com filtros avançados.', 2, 1, DATEADD('DAY', -15, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'E-commerce')),
    ('Configurar CDN para imagens',             'Subir imagens de produto para S3 e distribuir via CloudFront.',          1, 1, DATEADD('DAY', -10, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'E-commerce'));

-- App Mobile de Delivery
INSERT INTO tasks (title, description, priority, status, created_at, completed_at, project_id) VALUES
    ('Criar telas de onboarding',               'Fluxo de cadastro e login do usuário com validação de CPF.',             2, 3, DATEADD('DAY', -44, CURRENT_TIMESTAMP), DATEADD('DAY', -38, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'App Mobile de Delivery')),
    ('Integrar API de mapas',                   'Exibir mapa interativo com localização do entregador em tempo real.',    3, 3, DATEADD('DAY', -42, CURRENT_TIMESTAMP), DATEADD('DAY', -35, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'App Mobile de Delivery')),
    ('Implementar notificações push',            'Alertas de status do pedido via Firebase Cloud Messaging.',              3, 2, DATEADD('DAY', -28, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'App Mobile de Delivery')),
    ('Desenvolver sistema de avaliação',         'Usuário avalia entregador e restaurante após a entrega.',                2, 2, DATEADD('DAY', -20, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'App Mobile de Delivery')),
    ('Testes de usabilidade com usuários reais', 'Sessões de teste com 10 usuários para validar fluxo de pedido.',        1, 1, DATEADD('DAY', -8,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'App Mobile de Delivery'));

-- Portal de RH Interno
INSERT INTO tasks (title, description, priority, status, created_at, completed_at, project_id) VALUES
    ('Cadastro de colaboradores',               'CRUD completo com importação via planilha Excel.',                       3, 3, DATEADD('DAY', -29, CURRENT_TIMESTAMP), DATEADD('DAY', -22, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'Portal de RH Interno')),
    ('Módulo de controle de ponto',             'Registro de entrada/saída com exportação para folha de pagamento.',      3, 2, DATEADD('DAY', -25, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Portal de RH Interno')),
    ('Fluxo de solicitação de férias',          'Calendário de férias com aprovação do gestor e integração ao RH.',       2, 2, DATEADD('DAY', -18, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Portal de RH Interno')),
    ('Relatório de banco de horas',             'Dashboard com horas extras, saldo e projeção mensal por colaborador.',   2, 1, DATEADD('DAY', -12, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Portal de RH Interno')),
    ('Integração com sistema de folha',         'Envio automático dos dados de ponto para o sistema Totvs.',              3, 1, DATEADD('DAY', -5,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Portal de RH Interno'));

-- Refatoração do Legado
INSERT INTO tasks (title, description, priority, status, created_at, completed_at, project_id) VALUES
    ('Mapear dependências do monolito',         'Levantar todos os módulos, chamadas internas e acoplamentos existentes.',3, 3, DATEADD('DAY', -19, CURRENT_TIMESTAMP), DATEADD('DAY', -15, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'Refatoração do Legado')),
    ('Extrair microsserviço de autenticação',   'Separar o módulo de auth em serviço independente com JWT.',              3, 3, DATEADD('DAY', -17, CURRENT_TIMESTAMP), DATEADD('DAY', -12, CURRENT_TIMESTAMP), (SELECT id FROM projects WHERE name = 'Refatoração do Legado')),
    ('Definir contratos de API entre serviços', 'Documentar contratos OpenAPI 3.0 para cada microsserviço.',              2, 2, DATEADD('DAY', -12, CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Refatoração do Legado')),
    ('Configurar service mesh com Istio',       'Gerenciamento de tráfego, observabilidade e segurança entre serviços.',  2, 1, DATEADD('DAY', -7,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Refatoração do Legado')),
    ('Migrar banco de dados para multi-tenant', 'Separar schemas por domínio e implementar estratégia de migração.',      3, 1, DATEADD('DAY', -3,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Refatoração do Legado'));

-- Gateway de Pagamento
INSERT INTO tasks (title, description, priority, status, created_at, completed_at, project_id) VALUES
    ('Integrar API do Stripe',                  'Suporte a cartão de crédito/débito com 3DS e recorrência.',              3, 2, DATEADD('DAY', -9,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Gateway de Pagamento')),
    ('Implementar pagamento via Pix',            'Geração de QR Code dinâmico e webhook de confirmação pelo banco.',       3, 2, DATEADD('DAY', -8,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Gateway de Pagamento')),
    ('Desenvolver fila de retry de cobranças',   'Reprocessar transações falhas com backoff exponencial via RabbitMQ.',   2, 1, DATEADD('DAY', -6,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Gateway de Pagamento')),
    ('Criar dashboard de conciliação',           'Painel para conferência de transações entre sistema e extrato bancário.',1, 1, DATEADD('DAY', -4,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Gateway de Pagamento')),
    ('Testes de carga no fluxo de checkout',     'Simular 1000 transações/min com k6 e validar SLA de resposta.',         3, 1, DATEADD('DAY', -2,  CURRENT_TIMESTAMP), NULL,                                    (SELECT id FROM projects WHERE name = 'Gateway de Pagamento'));

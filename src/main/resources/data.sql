-- Insertar recetas de ejemplo
INSERT INTO recipes (name, description, default_milk_volume, default_starter_amount, 
                     heating_temperature, heating_duration, inoculation_temperature, 
                     incubation_temperature, min_incubation_time, max_incubation_time, 
                     refrigeration_time, difficulty, active)
VALUES 
('Yogurt Natural Clásico', 'Receta tradicional de yogurt natural', 2.0, 4.0, 
 85.0, 30, 43.0, 
 42.0, 6, 8, 
 4, 'BEGINNER', true),

('Yogurt Griego Cremoso', 'Yogurt estilo griego, más cremoso y espeso', 3.0, 6.0, 
 90.0, 45, 45.0, 
 44.0, 8, 10, 
 6, 'INTERMEDIATE', true),

('Yogurt Proteico', 'Alto en proteínas, ideal para deportistas', 2.5, 8.0, 
 82.0, 25, 42.0, 
 41.0, 10, 12, 
 4, 'ADVANCED', true);

-- Insertar ingredientes para las recetas
INSERT INTO ingredients (name, quantity, unit, recipe_id, optional, notes)
VALUES 
-- Ingredientes para Yogurt Natural Clásico (receta id 1)
('Leche entera', 2.0, 'litros', 1, false, 'Leche fresca pasteurizada'),
('Yogurt natural con cultivos activos', 4.0, 'cucharadas', 1, false, 'Como iniciador'),

-- Ingredientes para Yogurt Griego (receta id 2)
('Leche entera', 3.0, 'litros', 2, false, 'Leche fresca pasteurizada'),
('Yogurt griego natural', 6.0, 'cucharadas', 2, false, 'Como iniciador'),
('Leche en polvo', 100.0, 'gramos', 2, true, 'Opcional para más cremosidad'),

-- Ingredientes para Yogurt Proteico (receta id 3)
('Leche descremada', 2.5, 'litros', 3, false, 'Leche fresca pasteurizada'),
('Yogurt natural', 8.0, 'cucharadas', 3, false, 'Como iniciador'),
('Proteína de suero de leche', 50.0, 'gramos', 3, true, 'Sin sabor');
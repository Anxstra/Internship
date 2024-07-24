INSERT INTO categories (name, type, parent_id, version)
    VALUES ('Electronics', 'GROUP', NULL, 0);

INSERT INTO categories (name, type, parent_id, version)
    VALUES ('Computers', 'CATEGORY', 101, 0),
           ('Home Appliances', 'CATEGORY', 101, 0);

INSERT INTO categories (name, type, parent_id, version)
    VALUES ('Laptops', 'SUBCATEGORY', 102, 0),
           ('Desktops', 'SUBCATEGORY', 102, 0),
           ('Smartphones', 'SUBCATEGORY', 102, 0),
           ('Refrigerators', 'SUBCATEGORY', 103, 0),
           ('Washing Machines', 'SUBCATEGORY', 103, 0),
           ('Microwaves', 'SUBCATEGORY', 103, 0);

INSERT INTO descriptions (text, weight, length, width, depth, version)
    VALUES ('Slim and portable laptop', 1200, 350, 240, 20, 0),
           ('High performance desktop', 8500, 500, 200, 450, 0),
           ('Latest model smartphone', 180, 150, 70, 8, 0),
           ('Energy efficient refrigerator', 60000, 800, 700, 1800, 0),
           ('Automatic washing machine', 70000, 600, 600, 1000, 0),
           ('Quick heating microwave', 12000, 500, 450, 300, 0),
           ('Lightweight and sleek', 169, 152, 71, 18, 0),
           ('Powerful gaming desktop', 9500, 550, 220, 480, 0),
           ('Affordable smartphone', 160, 140, 65, 7, 0);

INSERT INTO products (name, price, quantity, delivery_period, image_url, category_id, description_id, version)
    VALUES ('MacBook Air', 999.99, 10, 7, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721842398/rest%20api/macbook-air.jpg', 104, 101, 0),
           ('Dell XPS', 1299.99, 5, 10, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721842784/rest%20api/dell-xps.jpg', 105, 102, 0),
           ('iPhone 13', 799.99, 20, 5, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721842946/rest%20api/iphone-13.jpg', 106, 103, 0),
           ('Samsung Galaxy S21', 699.99, 15, 7, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721843020/rest%20api/galaxy-s21.jpg', 106, 107, 0),
           ('LG Refrigerator', 1199.99, 7, 14, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721843153/rest%20api/lg-fridge.jpg', 107, 104, 0),
           ('Whirlpool Washing Machine', 899.99, 8, 10, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721843216/rest%20api/whirlpool-washing-machine.png', 108, 105, 0),
           ('Panasonic Microwave', 199.99, 12, 7, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721843323/rest%20api/panasonic-microvawe.jpg', 109, 106, 0),
           ('HP Omen Desktop', 1499.99, 4, 14, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721843373/rest%20api/hp-omen.jpg', 105, 108, 0),
           ('Sony Xperia', 499.99, 10, 5, 'https://res.cloudinary.com/dmizbh1cu/image/upload/v1721844095/rest%20api/sony-xperia.jpg', 106, 109, 0);
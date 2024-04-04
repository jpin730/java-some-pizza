DROP procedure IF EXISTS `take_random_pizza_order`;
DELIMITER $$ CREATE PROCEDURE `take_random_pizza_order`(
    IN customer_id VARCHAR(15),
    IN method CHAR(1),
    OUT order_taken BOOL
) BEGIN
DECLARE random_pizza_id INT;
DECLARE random_pizza_price DECIMAL(5, 2);
DECLARE price_with_discount DECIMAL(5, 2);
DECLARE WITH_ERRORS BOOL DEFAULT FALSE;
DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN
SET WITH_ERRORS = TRUE;
END;
SELECT id,
    price INTO random_pizza_id,
    random_pizza_price
FROM pizza
WHERE available = 1
ORDER BY RAND()
LIMIT 1;
SET price_with_discount = random_pizza_price * (1 - 0.20);
START TRANSACTION;
INSERT INTO pizza_order (
        customer_id,
        date,
        total,
        method,
        comments
    )
VALUES (
        customer_id,
        SYSDATE(),
        price_with_discount,
        method,
        '20% OFF PIZZA RANDOM PROMOTION'
    );
INSERT INTO order_item (id, order_id, pizza_id, quantity, price)
VALUES (
        1,
        LAST_INSERT_ID(),
        random_pizza_id,
        1,
        random_pizza_price
    );
IF WITH_ERRORS THEN
SET order_taken = FALSE;
ROLLBACK;
ELSE
SET order_taken = TRUE;
COMMIT;
END IF;
SELECT order_taken;
END $$ DELIMITER;
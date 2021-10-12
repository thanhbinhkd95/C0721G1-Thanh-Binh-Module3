DELIMITER $$

CREATE PROCEDURE view_list_user_procedure()
BEGIN
SELECT * from users;
END$$

DELIMITER ;


-- sửa thông tin user
DELIMITER $$

CREATE PROCEDURE update_user_procedure(p_id int, p_name varchar(50) , p_email varchar(50) , p_country varchar(50))
BEGIN
Update users
set `name` = p_name, email = p_email , country = p_country
where id = p_id;
END$$

DELIMITER ;

call update_user_procedure(1,"Hung","hung@gmail.com","Viet Nam");

-- xóa users

DELIMITER $$

CREATE PROCEDURE delete_user_procedure(p_id int)
BEGIN
DELETE FROM users
WHERE id = p_id;
END$$

DELIMITER ;
call delete_user_procedure(8);
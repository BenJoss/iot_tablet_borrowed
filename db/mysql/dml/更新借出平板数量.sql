set autocommit=0;

start transaction;

SELECT BORROW_NUM FROM t_iot_device_borrow WHERE VERIFY_CODE=8484 FOR UPDATE;

UPDATE t_iot_device_borrow SET BORROW_NUM=20 WHERE VERIFY_CODE=8484 ;

commit ;
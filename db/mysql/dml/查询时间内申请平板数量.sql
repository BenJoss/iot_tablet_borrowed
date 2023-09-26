SELECT 
		
		( SELECT
			SUM( BORROW_NUM ) 
		FROM
			t_iot_device_borrow 
		WHERE
			ALLDAY_TIME = STR_TO_DATE( "2023-09-26 00:00:00", "%Y-%m-%d %H:%i:%s" ) FOR UPDATE ) AS all_day_num,
			
	 ( SELECT
			SUM( BORROW_NUM ) 
		FROM
			t_iot_device_borrow 
		WHERE
			MORN_TIME = STR_TO_DATE( "2023-09-25 08:00:00", "%Y-%m-%d %H:%i:%s" ) FOR UPDATE) AS morn_day_num,
			
		(SELECT
			SUM( BORROW_NUM ) 
		FROM
			t_iot_device_borrow 
		WHERE
			AFTER_TIME = STR_TO_DATE( "2023-09-25 14:00:00", "%Y-%m-%d %H:%i:%s" ) FOR UPDATE) AS after_day_num
	FROM DUAL
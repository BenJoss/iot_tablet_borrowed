-- 借用状态：1：待借用、2：借用中、3：完结、4：异常、5：取消
-- EXPLAIN
SELECT
	a.ROOM_ID AS ROOM_NAME,
	a.MEET_ID AS MEET_NAME,
	a.VERIFY_CODE,
	a.BORROW_NUM,
	a.USER_NAME,
	a.BORROWED_STATUS,
	date_format( a.START_TIME, '%Y-%m-%d %T' ) AS BORROW_TIME,
	b.RETURN_NUM 
FROM
	t_iot_device_borrow a
	LEFT JOIN
	t_iot_device_return b 
	on a.VERIFY_CODE = b.VERIFY_CODE
WHERE
	1 = 1 
	AND a.BORROWED_STATUS IN('1')
	AND a.USER_ID =''
	ORDER BY a.CREATE_TIME DESC
	LIMIT 0,10
	
	-- ORDER BY a.CREATE_TIME DESC
	
	
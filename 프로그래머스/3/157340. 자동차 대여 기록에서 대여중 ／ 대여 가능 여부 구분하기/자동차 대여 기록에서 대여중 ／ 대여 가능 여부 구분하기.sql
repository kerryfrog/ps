
SELECT c.car_id as CAR_ID, (
    CASE 
        WHEN rc.car_id is null THEN '대여 가능'
        ELSE '대여중'
    END
) as AVAILABILITY
# SELECT * 
FROM (select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY c group by car_id order by car_id desc ) as c 
left outer join (SELECT car_id FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
WHERE START_DATE <= '2022-10-16'
    and END_DATE >= '2022-10-16' group by car_id ) AS rc 
on c.car_id = rc.car_id

# SELECT * FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
# WHERE START_DATE <= '2022-10-16'
#     and END_DATE >= '2022-10-16' 


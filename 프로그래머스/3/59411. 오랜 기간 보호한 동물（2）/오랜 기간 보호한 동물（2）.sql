-- 코드를 입력하세요
SELECT ao.ANIMAL_ID, ao.NAME
FROM ANIMAL_OUTS ao 
left outer join ANIMAL_INS ai
on ao.ANIMAL_ID = ai.ANIMAL_ID
where ai.ANIMAL_ID is not null
Order by ao.DATETIME - ai.DATETIME desc
limit 2



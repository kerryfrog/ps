SELECT 
    CH.HISTORY_ID,
    FLOOR(
        CASE 
            WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 90 THEN 
                (100 - (SELECT CAST(REPLACE(DISCOUNT_RATE, '%', '') AS UNSIGNED) 
                        FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
                        WHERE CAR_TYPE = '트럭' AND DURATION_TYPE = '90일 이상')) 
                * DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1) / 100
            
            WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN 
                (100 - (SELECT CAST(REPLACE(DISCOUNT_RATE, '%', '') AS UNSIGNED) 
                        FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
                        WHERE CAR_TYPE = '트럭' AND DURATION_TYPE = '30일 이상')) 
                * DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1) / 100
            
            WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 7 THEN 
                (100 - (SELECT CAST(REPLACE(DISCOUNT_RATE, '%', '') AS UNSIGNED) 
                        FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
                        WHERE CAR_TYPE = '트럭' AND DURATION_TYPE = '7일 이상')) 
                * DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1) / 100
            
            ELSE 
                DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1)
        END
    ) AS FEE
FROM 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY CH
JOIN 
    CAR_RENTAL_COMPANY_CAR C ON CH.CAR_ID = C.CAR_ID
WHERE 
    C.CAR_TYPE = '트럭'
ORDER BY 
    FEE DESC, 
    HISTORY_ID DESC;

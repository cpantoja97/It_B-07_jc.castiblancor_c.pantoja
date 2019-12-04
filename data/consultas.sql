--RFC 1 MOSTRAR LA CANTIDAD DE SERVICIOS PRESTADOS POR CADA IPS DURANTE UN PERIODO DE TIEMPO Y EN EL A�O CORRIDO
SELECT PRESTACIONSERVICIO.ID_IPS, COUNT(*)
FROM IPS NATURAL INNER JOIN PRESTACIONSERVICIO
WHERE FECHAHORA > a�o AND FECHAHORA BETWEEN fMin AND fMax
GROUP BY IPS.ID_IPS
;

--RFC2 - MOSTRAR LOS 20 SERVICIOS M�S SOLICITADOS. Los que fueron m�s solicitados en un per�odo de tiempo dado.
SELECT *
FROM
    (
    SELECT SERVICIOS.NOMBRE, count(*) as cuenta
    FROM SERVICIOS NATURAL INNER JOIN RESERVASERVICIO
    GROUP BY SERVICIOS.NOMBRE
    ORDER BY cuenta DESC
    )
WHERE ROWNUM <20
;

--RFC3 - MOSTRAR EL �NDICE DE USO DE CADA UNO DE LOS SERVICIOS PROVISTOS
SELECT SERVICIOS.NOMBRE, uso/capacidad
FROM SERVICIOS 
    INNER JOIN
    (
    SELECT PRESTACIONSERVICIO.ID_SERVICIO AS id, COUNT(*) AS uso
    FROM PRESTACIONSERVICIO
    GROUP BY PRESTACIONSERVICIO.ID_SERVICIO
    ) aux1 ON SERVICIOS.ID_SERVICIO = aux1.id
    INNER JOIN 
    (
    SELECT SERVICIOSIPS.ID_SERVICIO AS id, SUM(SERVICIOSIPS.CAPACIDAD) AS capacidad
    FROM SERVICIOSIPS
    GROUP BY SERVICIOSIPS.ID_SERVICIO
    ) aux2 ON aux1.id = aux2.id
;

--RFC4 - MOSTRAR LOS SERVICIOS QUE CUMPLEN CON CIERTA CARACTER�STICA Toda la informaci�n del servicio.
--Las caracter�sticas son, por ejemplo, fecha de prestaci�n en un rango de tiempo, registrados por 
--cierto recepcionista, son de cierto tipo, han sido solicitados m�s de X veces en un rango de fechas. 
--Consulte cualquier combinaci�n de caracter�sticas en la consulta.

SELECT SERVICIOS.*
FROM SERVICIOS
WHERE SERVICIOS.? = ?
;

-- ARS atributo Reserva Servicio *** ? Valor
SELECT SERVICIOS.*
FROM SERVICIOS INNER JOIN RESERVASERVICIO ON SERVICIOS.ID_SERVICIO = RESERVASERVICIO.ID_SERVICIO
WHERE RESERVASERVICIO.ARS = ? 
;
-- SERVICIOS PRESTADOS POR EL RECEPCIONISTA ?
SELECT SERVICIOS.*
FROM SERVICIOS INNER JOIN PRESTACIONSERVICIO ON SERVICIOS.ID_SERVICIO = PRESTACIONSERVICIO.ID_SERVICIO
WHERE PRESTACIONSERVICIO.ID_RECEPCIONISTA = ? 
;

-- RFC5 - MOSTRAR LA UTILIZACI�N DE SERVICIOS DE EPSANDES POR UN AFILIADO DADO, EN UN RANGO DE FECHAS INDICADO.
--Recuerde que un afiliado puede solicitar servicios de salud cuantas veces lo requiera. Considere tanto la 
--reserva como el uso efectivo de los servicios de salud.

SELECT SERVICIOS.NOMBRE, COUNT(*)
FROM SERVICIOS INNER JOIN PRESTACIONSERVICIO ON SERVICIOS.ID_SERVICIO = PRESTACIONSERVICIO.ID_SERVICIO
WHERE PRESTACIONSERVICIO.NUMDOC = ? AND PRESTACIONSERVICIO.FECHAHORA BETWEEN 'XX-YY-ZZ' AND 'XX-YY-ZZ'
GROUP BY SERVICIOS.NOMBRE
;

--RFC 6 Analisar operacion EPSAndes
-- iw WEEK dd DAY MM MONTH YYYY YEAR
SELECT *
FROM
    (
    select TRUNC( reservaServicio.fechaHora,'DD' ), count(*) as cuenta
    from SERVICIOS inner join reservaServicio on servicios.id_servicio = reservaServicio.id_servicio  
    where SERVICIOS.NOMBRE = 'Oftalmologia'
    Group by TRUNC( reservaServicio.fechaHora, 'DD' )
    
    Order by cuenta desc 
    
    )
    
WHERE ROWNUM <3
;

SELECT *
FROM
    (
    select TRUNC( reservaServicio.fechaHora,'IW' ), count(*) as cuenta
    from SERVICIOS inner join reservaServicio on servicios.id_servicio = reservaServicio.id_servicio
    Group by TRUNC( reservaServicio.fechaHora, 'IW' )
    Order by cuenta asc
    )
    
WHERE ROWNUM <3
;

SELECT *
FROM
    (
    select TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) as cuenta
    from SERVICIOS inner join prestacionServicio on servicios.id_servicio = prestacionServicio.id_servicio
    Group by TRUNC( prestacionServicio.fechaHora, 'IW' )
    Order by cuenta desc
    )
    
WHERE ROWNUM <3
;

--RFC 7 Afiliados exigentes

select afiliados.numdoc, count(*) as cuenta, sum(*)
from (afiliados inner join 
    (
    select *prestacionServicio )
inner join servicio on afiliados.numdoc = prestacionservicio.numdoc
group by afiliados.numdoc;

select aux.numdoc, count(*), sum(cuenta) as suma
from 
    (
    select prestacionServicio.numdoc, servicios.tipo, count(*) as cuenta
    from prestacionServicio inner join servicios on servicios.id_servicio = prestacionservicio.id_servicio
    group by prestacionServicio.numdoc , servicios.tipo
    ) aux 
    group by aux.numdoc
    having count(*)>1 and sum(cuenta)>1;
    
    commit;
--RFC 8 Servicios con poca demanda
select servicios.nombre 
from servicios left outer join
    (
    select aux.nombre
    from
        (
        select servicios.nombre, TRUNC( reservaServicio.fechaHora,'IW' )
        from SERVICIOS inner join reservaServicio on servicios.id_servicio = reservaServicio.id_servicio
        Group by servicios.nombre, TRUNC( reservaServicio.fechaHora, 'IW' )
        having count(*) >2
        ) aux
    group by aux.nombre
    having count(*)=1
    ) aux2 
on servicios.nombre = aux2.nombre
where aux2.nombre is null;

--RFC 9 CONSULTAR LA PRESTACI�N DE SERVICIOS EN EPSANDES
select usuarios.nombre, afiliados.*, servicios.tipo, prestacionservicio.id_servicio, prestacionservicio.id_ips, prestacionservicio.fechahora
from usuarios, afiliados, prestacionservicio, servicios
where usuarios.numdoc = afiliados.numdoc and afiliados.numdoc = prestacionservicio.numdoc and servicios.id_servicio = prestacionservicio.id_servicio
    and prestacionservicio.fechahora between '27-oct-18' and '29-dec-18' 
    and servicios.id_servicio = 4 
    and servicios.tipo = 1 
    and prestacionservicio.ID_IPS = 1
order by prestacionservicio.fechahora
;

--RFC 10 
    --CRITERIO SERVICIOS NO UTILIZADOS
    --SIN AGRUPAR
    SELECT t1.*
    FROM SERVICIOS t1 LEFT OUTER JOIN (
            SELECT *
            FROM PRESTACIONSERVICIO
            WHERE 1=1
            --AND fechahora between '27-oct-18' and '29-oct-18'
            AND prestacionservicio.ID_IPS = 3
            ) AUX_PRESTACION
        ON t1.ID_SERVICIO = AUX_PRESTACION.ID_SERVICIO
    WHERE AUX_PRESTACION.FECHAHORA IS NULL
        --AND t1.tipo = 1
    ORDER BY t1.nombre
    ;
    
    
    --CRITERIO GENTE 
    --SIN AGRUPAR
    SELECT t1.NUMDOC, t1.NOMBRE, t1.FECHANACIMIENTO, t1.CORREO
    FROM (
            SELECT USUARIOS.*, AFILIADOS.FECHANACIMIENTO 
            FROM AFILIADOS INNER JOIN USUARIOS ON AFILIADOS.NUMDOC = USUARIOS.NUMDOC
            ) t1 LEFT OUTER JOIN (
            SELECT PRESTACIONSERVICIO.*
            FROM PRESTACIONSERVICIO INNER JOIN SERVICIOS ON PRESTACIONSERVICIO.ID_SERVICIO = SERVICIOS.ID_SERVICIO
            WHERE 1 = 1
            --AND fechahora between '27-oct-17' and '29-oct-18'
            --AND SERVICIOS.TIPO = 3
            --AND SERVICIOS.ID_SERVICIO = 1
            ) AUX_PRESTACION
        ON t1.NUMDOC = AUX_PRESTACION.NUMDOC
    WHERE AUX_PRESTACION.FECHAHORA IS NULL
    ORDER BY t1.NUMDOC ASC
    ;

    --CRITERIO IPS
    --SIN AGRUPAR
    SELECT t1.*
    FROM (
            SELECT IPS.*, SERVICIOSIPS.ID_SERVICIO, SERVICIOS.NOMBRE NOMSERVICIO, SERVICIOS.TIPO
            FROM IPS, SERVICIOSIPS, SERVICIOS
            WHERE IPS.ID_IPS = SERVICIOSIPS.ID_IPS AND SERVICIOSIPS.ID_SERVICIO = SERVICIOS.ID_SERVICIO
        ) t1 LEFT OUTER JOIN (
            SELECT PRESTACIONSERVICIO.*, SERVICIOS.TIPO
            FROM PRESTACIONSERVICIO INNER JOIN SERVICIOS ON PRESTACIONSERVICIO.ID_SERVICIO = SERVICIOS.ID_SERVICIO
            WHERE 1 = 1
            --AND fechahora between '27-oct-12' and '29-oct-12'
        ) AUX_PRESTACION
        ON t1.ID_SERVICIO = AUX_PRESTACION.ID_SERVICIO AND t1.ID_IPS = AUX_PRESTACION.ID_IPS
    WHERE AUX_PRESTACION.FECHAHORA IS NULL
        AND t1.ID_SERVICIO = 1
        --AND t1.TIPO = 1
    ORDER BY t1.ID_IPS desc
    ;


--RFC 11 CONSULTAR FUNCIONAMIENTO

-- Super consulta de la muerte RFC 11
select t1.week, t1.cantidad, t2.serviciomas, t3.serviciomenos, t4.ipsmas, t5.ipsmenos, t6.afiliado, t7.tipomenos, t8.tipomas
from
(
    select week, (
    select count(*) as cantidad
    from afiliados
    )- count(*) as cantidad
    from (
        select TRUNC( prestacionServicio.fechaHora, 'IW' ) as week, prestacionservicio.numdoc
        from prestacionServicio
        Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), numdoc
    )
    group by week
) t1
,
(
    select week, serviciomas
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_servicio as serviciomas, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_servicio
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc
        )
    where rn = 1
) t2
,
(
    select week, serviciomenos
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_servicio as serviciomenos, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_servicio
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t3
,
(
    select week, ipsmas
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_ips as ipsmas, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_ips
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc
        )
    where rn = 1
) t4
,
(
    select week, ipsmenos
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_ips as ipsmenos, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_ips
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t5
,
(
    select week, afiliado
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, numdoc as afiliado, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), numdoc
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t6
,
(
    select week, tipomenos
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, servicios.tipo as tipomenos, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio inner join servicios 
        on prestacionservicio.id_servicio = servicios.id_servicio
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), servicios.tipo
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc
        )
    where rn = 1
) t7
,
(
    select week, tipomas
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, servicios.tipo as tipomas, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio inner join servicios 
        on prestacionservicio.id_servicio = servicios.id_servicio
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), servicios.tipo
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t8
 where t1.week=t2.week and t2.week=t3.week and t3.week=t4.week and t4.week=t5.week
 and t5.week=t6.week and t6.week=t7.week and t7.week=t8.week
 order by t1.week
 ;

--RFC 12 CONSULTAR LOS AFILIADOS COSTOSOS
-- afiliados que van cada mes. 
select numdoc, count(*) from (
    select afiliados.numdoc, TRUNC( prestacionServicio.fechaHora, 'MM' ) as meses
    from afiliados, prestacionservicio 
    where afiliados.numdoc = prestacionservicio.numdoc
    group by afiliados.numdoc, TRUNC( prestacionServicio.fechaHora, 'MM' )
)
group by numdoc
having count(*)=24
;

-- afiliados que siempre requieren servicios medicos especializados
select afiliados.numdoc, count(*) as total , sum(case when servicios.tipo = 7 then 1 else 0 end) as especial
from afiliados, prestacionservicio, servicios
where afiliados.numdoc = prestacionservicio.numdoc and prestacionservicio.id_servicio = servicios.id_servicio
group by afiliados.numdoc
having count(*) = sum(case when servicios.tipo = 7 then 1 else 0 end)
;

-- Tabla 1
SELECT *
FROM ROLUSUARIO
;

-- Tabla 2
SELECT *
FROM IPS
;

-- Tabla 3
SELECT *
FROM SERVICIOS
;

-- Tabla 4
SELECT *
FROM USUARIOS
;

-- Tabla 5
SELECT *
FROM MEDICOS
;

-- Tabla 6
SELECT *
FROM AFILIADOS
;

-- Tabla 7
SELECT *
FROM RECEPCIONISTAS
;

-- Tabla 8
SELECT *
FROM SERVICIOSIPS
;

-- Tabla 9
SELECT *
FROM MEDICOSABSCRITOS
;

-- Tabla 10
SELECT *
FROM SERVICIOSMEDICOS
;

-- Tabla 11
SELECT *
FROM RECETAS
;

-- Tabla 12
SELECT *
FROM MEDICAMENTOS
;

-- Tabla 13
SELECT *
FROM ITEMSRECETA
;

-- Tabla 14
SELECT *
FROM ORDENES
;

-- Tabla 15
SELECT *
FROM RESERVASERVICIO
;

-- Tabla 16
SELECT *
FROM PRESTACIONSERVICIO
;


-- Super consulta de la muerte RFC 11
select t1.week, t1.cantidad, t2.serviciomas, t3.serviciomenos, t4.ipsmas, t5.ipsmenos, t6.afiliado, t7.tipomenos, t8.tipomas
from
(
    select week, (
    select count(*) as cantidad
    from afiliados
    )- count(*) as cantidad
    from (
        select TRUNC( prestacionServicio.fechaHora, 'IW' ) as week, prestacionservicio.numdoc
        from prestacionServicio
        Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), numdoc
    )
    group by week
) t1
,
(
    select week, serviciomas
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_servicio as serviciomas, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_servicio
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc
        )
    where rn = 1
) t2
,
(
    select week, serviciomenos
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_servicio as serviciomenos, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_servicio
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t3
,
(
    select week, ipsmas
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_ips as ipsmas, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_ips
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc
        )
    where rn = 1
) t4
,
(
    select week, ipsmenos
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_ips as ipsmenos, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_ips
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t5
,
(
    select week, afiliado
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, numdoc as afiliado, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio 
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), numdoc
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t6
,
(
    select week, tipomenos
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, servicios.tipo as tipomenos, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio inner join servicios 
        on prestacionservicio.id_servicio = servicios.id_servicio
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), servicios.tipo
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc
        )
    where rn = 1
) t7
,
(
    select week, tipomas
    from
        (
        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, servicios.tipo as tipomas, count(*), 
            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) 
            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn
        from prestacionservicio inner join servicios 
        on prestacionservicio.id_servicio = servicios.id_servicio
        group by TRUNC( prestacionServicio.fechaHora,'IW' ), servicios.tipo
        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc
        )
    where rn = 1
) t8
 where t1.week=t2.week  and t1.week=t3.week  and t1.week=t4.week and t1.week=t5.week 
 and t1.week=t6.week and t1.week=t7.week and t1.week=t8.week 
 ;
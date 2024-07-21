-------------------------------------------
-- Lecture 02 - Relational Database Language
-------------------------------------------

-- Pattern Matching in SQL 
SELECT title
FROM Movies
WHERE  title LIKE 'Star ____';

-- 
SELECT title
FROM Movies
WHERE  title LIKE '%''s%';

-- 
-- The Truth-Value UNKNOWN 
SELECT *
FROM Movies;

SELECT *
FROM Movies
WHERE length <= 120 OR length > 120;

-- Ordering the Output (Cont’d)
INSERT INTO Movies VALUES ('Apple Woman', 1990, 119, 'comedy', 'Disney', 999);
INSERT INTO Movies VALUES ('Apple Man', 1990, 120, 'comedy', 'Disney', 999);

SELECT *
FROM Movies
WHERE studioName='Disney' AND year=1990
ORDER BY length, title;

-- Products and Joins in SQL (Cont’d)
SELECT name
FROM Movies, MovieExec
WHERE title= 'Star Wars' AND producerC = cert;

-- Subqueries that Produce Scalar Values: Example
SELECT name
FROM MovieExec
WHERE cert =
        (SELECT producerC
         FROM Movies
         WHERE title = 'Star Wars'
         );
		 
SELECT name
FROM Movies, MovieExec
WHERE title= 'Star Wars' AND producerC = cert;

-- Conditions Involving Relations
SELECT name
FROM MovieExec
WHERE cert IN
        (SELECT producerC
         FROM Movies
         WHERE (title, year) IN
                 (SELECT movieTitle, movieYear
                  FROM StarsIn
                  WHERE starName = 'Harrison Ford'
                 )
         );

-- Unfortunately, using tuple in the IN clause is not supported in SQL Server
-- Use the EXISTS operator instead
SELECT name
FROM MovieExec
WHERE cert IN
        (SELECT producerC
         FROM Movies
         WHERE EXISTS
                 (SELECT *
                  FROM StarsIn
                  WHERE Movies.title = StarsIn.movieTitle AND
					    Movies.year = StarsIn.movieYear AND
					    starName = 'Harrison Ford'
                 )
         );
                 
		 
SELECT DISTINCT name
FROM MovieExec, Movies, StarsIn
WHERE cert = producerC AND
               title = movieTitle AND
               year = movieYear AND
               starName = 'Harrison Ford';

-- Correlated Subqueries: Example

SELECT * FROM Movies;

SELECT title
FROM Movies Old
WHERE year < ANY
        (SELECT year
         FROM Movies
         WHERE title = Old.title
        );
        
INSERT INTO Movies VALUES ('King Kong', 2014, 100, 'drama', 'Universal', 345);

-- Subqueries in FROM Clauses
SELECT name
FROM MovieExec, (SELECT producerC
                                  FROM Movies, StarsIn
                                 WHERE title = movieTitle AND
                                                year = movieYear AND
                                                starName = 'Harrison Ford'
                                 ) Prod
WHERE cert = Prod.producerC;


-- Aggregation Operators: Example
SELECT AVG(netWorth)
FROM MovieExec;

SELECT COUNT(*)
FROM StarsIn;

SELECT COUNT(starName)
FROM StarsIn;

SELECT COUNT(DISTINCT starName)
FROM StarsIn;

SELECT studioName, SUM(length)
FROM Movies
GROUP BY studioName;


SELECT name, SUM(length)
FROM MovieExec, Movies
WHERE producerC = cert
GROUP BY name;

-- HAVING Clauses
SELECT name, SUM(length)
FROM MovieExec, Movies
WHERE producerC = cert AND netWorth > 10000000
GROUP BY name;

SELECT name, SUM(length)
FROM MovieExec, Movies
WHERE producerC = cert
GROUP BY name
HAVING MIN(year) < 1930
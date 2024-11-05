DROP TABLE job;
DROP TABLE skill;
DROP TABLE employer;

-- Part 1: list the columns and their data types in the table as a SQL comment.
	-- id INT
    -- employer VARCHAR(255)
    -- name VARCHAR(255)
    -- skills VARCHAR(255)

-- Part 2: write a query to list the names of the employers in St. Louis City. 
	-- Do NOT specify an ordering for the query results.
SELECT name
FROM employer
WHERE location = "St. Louis City";

-- Part 3: write the SQL statement to remove the job table.
DROP TABLE job;

-- Part 4: write a query to return the names of all skills that are attached to jobs in alphabetical order. 
SELECT *
FROM skill
INNER JOIN  job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC; 
 


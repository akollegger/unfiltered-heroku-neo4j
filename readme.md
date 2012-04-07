Unfiltered Heroku Neo4j
=======================

An example Scala/sbt project.

Install Neo4j for the Project
-----------------------------

    neo4j install
    neo4j start

Discover the Neo4j REST API
---------------------------

  sbt> console

  scala>

    // create an _executor_ for HTTP
    val h = new Http
    
    // define the base URL for the Neo4j Server, and check it out
    val neo4j = :/("localhost", 7474)
    h(neo4j >>> System.out)
    
    // define a reference to the "graph" base URL, and get it
    val g = neo4j / "db" / "data"
    h(g >>> System.out)
    
    // define Node base URL, create a node with a POST
    val node = g / "node"
    h((node POST) >>> System.out)

    // create another node, with property "foo" set to "bar"
    h((node << ("{\"foo\" : \"bar\"}", "application/json")) >>> System.out)

    // query with Cypher
    h((cypher << ("""{"query": "start n=node(0) return n"}""", "application/json")) >>> System.out)

Hook it up
----------

    heroku apps:create --stack cedar
    heroku addons:add neo4j
    heroku config
    // note the NEO4J values, and substitute them below
   

    sbt> console
    scala>

    val h = new Http
    val neo4j = (:/(NEO4J_HOST, NEO4J_PORT)) as (NEO4J_LOGIN, NEO4J_PASSWORD)

Then proceed as before (when accessing Neo4j on localhost)



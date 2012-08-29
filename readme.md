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
    
    // generate the base URL for the Neo4j Server
    def neo4j = :/("localhost", 7474).addHeader("Accept", "application/json")

    // query the base url, using `for` to apply the promise
    for (json <- h(neo4j OK as.String)) println(json)
    
    // define a reference to the "graph" base URL, and get it
    // note the awkward addition of a blank string to force the URL to include '/'
    def g = neo4j / "db/data/"
    for (json <- h(g OK as.String)) println(json)
    
    // define Node base URL, create a node with a POST
    def node = g / "node"
    h(node << Map() > as.String)

    // create another node, with property "foo" set to "bar"
    h(node << Map("foo" -> "bar") > as.String)

    // use node's id to get its properties
    h(node / "1/properties" OK as.String)

    // query with Cypher
    def cypher = g / "cypher"
    h(cypher << Map("query" -> "start n=node(*) return n") OK as.String)

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



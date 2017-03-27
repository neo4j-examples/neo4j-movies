package neo4j.movies

/**
 * Models a Person node in the graph database
 */
class Person {
    String name
    int born

    static hasMany = [appearances: CastMember]

    static constraints = {
        name blank:false
        born min:1900
    }
}

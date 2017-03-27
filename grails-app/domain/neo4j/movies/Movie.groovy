package neo4j.movies

/**
 * Models a movie node in the graph database
 */
class Movie {
    String title
    String tagline
    int released

    static hasMany = [cast: CastMember]

    static constraints = {
        released min:1900
        title blank:false
    }
}

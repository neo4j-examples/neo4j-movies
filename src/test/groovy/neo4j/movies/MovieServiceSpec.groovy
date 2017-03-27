package neo4j.movies

import grails.gorm.transactions.Rollback
import org.grails.datastore.gorm.neo4j.Neo4jDatastore
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class MovieServiceSpec extends Specification {

    @Shared @AutoCleanup Neo4jDatastore datastore = new Neo4jDatastore(getClass().getPackage())
    @Shared MovieService service = datastore.getService(MovieService)

    @Rollback
    void "Test search movies"() {
        given:
        new Movie(title: "The Matrix", tagline: "Free your mind", released: 1999).save(flush:true)

        expect:"to find results"
        service.search("Matr")
        service.search("The")
        !service.search("The Wrestler")
    }

    @Rollback
    void "Test find movie by title"() {
        given:
        new Movie(title: "The Matrix", tagline: "Free your mind", released: 1999).save(flush:true)

        expect:"The find movies by title"
        service.find("The Matrix")
        !service.find("The Wrestler")
    }
}

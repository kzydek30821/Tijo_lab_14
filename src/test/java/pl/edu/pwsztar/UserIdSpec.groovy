package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

import static pl.edu.pwsztar.UserIdChecker.Sex.*

class UserIdSpec extends Specification {

    @Unroll
    def "should check right pesel number length"(){
        given: "initial data"
        def user = new UserId(pesel);
        when: "get if pesel is correct length"
        def isCorrect = user.isCorrectSize();
        then: "check status code"
        isCorrect == status;

        where:
        pesel           | status
        "97011035233"   | true
        "85071756522"   | true
        "5245552555"    | false
        "97073002976"   | true
        "9707300"       | true
    }
    @Unroll
    def "should get right sex"(){
        given: "initial data"
        def user = new UserId(pesel);
        when: "get sex"
        def isCorrect = user.getSex();
        then: "check sex"
        isCorrect.get() == sex;

        where:
        pesel           | sex
        "97011035233"   | MAN
        "85071756522"   | WOMAN
        "97073002976"   | WOMAN
    }

    @Unroll
    def "should check if pesel number is correct"(){
        given: "initial data"
        def user = new UserId(pesel);
        when: "get if pesel number is correct"
        def isCorrect = user.isCorrect();
        then: "check status code"
        isCorrect == status;

        where:
        pesel           | status
        "97011035233"   | true
        "85071756523"   | false
        "97073002976"   | true
    }

    @Unroll
    def "should get right date from pesel number"(){
        given: "initial data"
        def user = new UserId(pesel);
        when: "get date"
        def isCorrect = user.getDate();
        then: "check if date match with pesel number"
        isCorrect.get() == date;

        where:
        pesel           | date
        "97011035233"   | "10-01-1997"
        "85071756522"   | "17-07-1985"
        "97073002976"   | "30-07-1997"
    }
}

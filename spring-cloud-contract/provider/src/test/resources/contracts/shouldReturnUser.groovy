import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return user details"
    request {
        method 'GET'
        url '/user/1'
    }
    response {
        status 200
        body([
                id: 1,
                name: "John Doe",
                email: "john.doe@example.com"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
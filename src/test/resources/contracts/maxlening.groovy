package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/bank/lening'
        body([
                "geboortedatum": 01-01-1974,
                "inkomen" : 20000
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                maxLening: 80000,
                levensverzekering: 20000,
                premie: 10
        ])
        headers {
            contentType('application/json')
        }
    }
}
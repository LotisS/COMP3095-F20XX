db = db.getSiblingDB("product-service");

db.createUser({
    user: "rootadmin",
    pwd: "password",
    roles: [
        {
            role: "readwrite",
            db: "product-service"
        },

    ]
});
// Create collection named "product"
db.createCollection("product");


//insert a sample document into the "product" collection
db.product.insert({
    name: "Sample Product",
    description: "This is sample product description",
    price:29.99
});

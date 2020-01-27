const express = require('express');
const request = require('request');
const admin = require('firebase-admin');

const Port = 8080;
const SHARE_DEFUALT = 'ZG';
var registrationToken = "";

const Alpha_key = 'HDWP781MGUTG7R9P';

var app = express();
app.use(express.json());
const serviceAccount = ("C:/Users/User/Desktop/client-d0f4e-firebase-adminsdk-7n12y-187bf9f11b.json")

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://client-d0f4e.firebaseio.com/"
});


app.post('/sharePrice', (req, res, next) => {
    console.log("POST request")
    let shareName = req.body.name || SHARE_DEFUALT;
    registrationToken = req.body.token;
    
    if (!shareName) {
        shareName = SHARE_DEFUALT;
    }

    console.log('The share name is ' + shareName);
    console.log('The given token is ' + registrationToken);

    var url = `https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=${shareName}&apikey=${Alpha_key}`;

    var sharePrice = 0;
    var first = 1;
    var updatePrice = 0;
    
    function timeout() {
        setTimeout(function () {
            first = 0;
            request(url, function (err, response, body) {
                if (err) {
                    console.log('error:', error);
                    response.status(400).json({ err: "Failed sending request to AlphaVantage" });
                } else {
                    let shareDetails = JSON.parse(body);
                    let globalQuote = shareDetails["Global Quote"];
                    if (globalQuote != undefined) {
                        updatePrice = globalQuote["05. price"];
                    } 
                    console.log(`Share ${shareName} current price is: ${sharePrice}`);
                    sharePrice = updatePrice;
                    sendDataToFireBase(registrationToken, shareName, sharePrice);

                    }                
            })
            timeout();
       }, first ? 1 : 15000
            )}
    timeout();
});

function sendDataToFireBase(token, shareName, price) {
    var payload = {
        data: {
            name: shareName,
            price: price
        },
        notification: {
            body: `The price of ${shareName} is ${price}`
        }
    };
    admin.messaging().sendToDevice(token, payload)
        .then(function (response) {
            console.log("Successfully sent message:", response);
        })
        .catch(function (error) {
            console.log("Error sending message:", error);
        });
}

app.listen(Port, () => {
    console.log(`Listening on port ${Port}`);
});
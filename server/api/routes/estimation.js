const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

const Schedule = require('../models/schedule');


router.get('/', (req, res, next) => {
    
    Schedule.find({id:{ $lt: 43}}).sort({id : 1}).exec().then(docs => {

        docs.forEach(element => { 

            var time = element['time']
            var hour = time.split(":")[0];
            var min  = time.split(":")[1];
            
            var year = '2013';
            var month = '04';
            var day = '18';

            var reserv = new Date(year,month, day, hour,min);

            var destTime = reserv.addHours(1)
            var n = destTime.toLocaleTimeString().match(/\d{2}:\d{2}|[AMP]+/g).join(' ');
            console.log(n);

            
            
          });


        res.status(200).json(docs)
    })


    // res.status(200).json({
    // message: 'Schedule is fetched',
    // res : 
    // });
});


router.post('/', (req, res, next) => {
    const order = {
        quantity: req.body.quantity,
        productID: req.body.productID
    };




    // dbConnection.find({"direction":"EAST", "id" : { $lt: 43 }}).sort( { id: 1 } ).then(abc =>{
    //     console.log(abc)
    // });
    

    res.status(201).json({
    message: 'Orders was fetched',
    order: order
    });
});


Date.prototype.addHours = function(h) {
    this.setTime(this.getTime() + (h*60*60*1000));
    return this;
}


module.exports = router;
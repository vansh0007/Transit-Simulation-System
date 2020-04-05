const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

const Schedule = require('../models/schedule');
const config = require('../models/configurations');


router.get('/updateTime' , (req, res, next) => {
    Schedule.find({id:{ $lt: 2}}).sort({id : 1}).exec().then(eastDocs => {

        console.log(eastDocs[0].time);

        var year = '2013';
        var month = '04';
        var day = '18';
        var whour = eastDocs[0].time.split(":")[0];
        var wmin  = eastDocs[0].time.split(":")[1];

        var wreserv = new Date(year,month, day, whour,wmin);


        console.log(new Date(wreserv));


//     { $dateFromString: {
//         dateString: "06-15-2018",
//         format: "%m-%d-%Y"
//    } }
    }).catch(e => {
        console.log(e);
    })

})

router.get('/east', (req, res, next) => {
    
    // var result = Schedule.find().exec().then();



    var countBus = 0;
    Schedule.find({id:{ $lt: 43}}).sort({id : 1}).exec().then(eastDocs => {

    Schedule.find({id:{ $gt: 42}}).sort({id : 1})
    .exec()
    .then(westDocs => {
        for (let i = 0; i < eastDocs.length; i++) {
            // Runs 5 times, with values of step 0 through 4.
            console.log(eastDocs[i]['time']);


            var eastStarttime = eastDocs[i]['time'];


            var westStarttime = westDocs[i]['time'];



            var whour = westStarttime.split(":")[0];
            var wmin  = westStarttime.split(":")[1];
            
            var year = '2013';
            var month = '04';
            var day = '18';

            var wreserv = new Date(year,month, day, whour,wmin);

            var wdestTime = wreserv.addHours(1)
            var westReachTime = wdestTime.toLocaleTimeString().match(/\d{2}:\d{2}|[AMP]+/g).join(' ');
            // console.log("west Reaching Time===> " + westReachTime);  


            var ehour = eastStarttime.split(":")[0];
            var emin  = eastStarttime.split(":")[1];
            
         

            var ereserv = new Date(year,month, day, ehour,emin);

            var edestTime = ereserv.addHours(1)
            var eastReachTime = edestTime.toLocaleTimeString().match(/\d{2}:\d{2}|[AMP]+/g).join(' ');
            console.log("eastReachTime Reaching Time===> " + eastReachTime);  
            
            if (eastStarttime < westReachTime){
                countBus++;
             }





        }
        console.log(countBus)



        res.status(200).json(eastDocs);

    }).catch(err => {
    console.log(err);
    })

        

        //   Schedule.update({'id': 1},
        //   {'$set' : {'create_time' : "test" }})
        //   .exec()
        //   .then(res => {console.log(res)})
        //   .catch(err => {console.log(err)})

    })


    // res.status(200).json({
    // message: 'Schedule is fetched',
    // res : 
    // });
});


router.get('/west', (req, res, next) => {
    
    var countBus = 0;
    Schedule.find({id:{ $gt: 42}}).sort({id : 1})
    .exec()
    .then(docs => {

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
            console.log("West===> " + n);  
            
             if ( element['time'] < n ){
                countBus++;
             }

             console.log(countBus)


          }          
          );

        //   Schedule.update({'id': 1},
        //   {'$set' : {'create_time' : "test" }})
        //   .exec()
        //   .then(res => {console.log(res)})
        //   .catch(err => {console.log(err)})

        res.status(200).json(docs)
    })
});


router.post('/', (req, res, next) => {
    config.find().exec().then(dbres => {

        console.log(req.body);

//Number of trips based on mileage
        let batteryConfig = req.body.battery;
        var trips = [];
        batteryConfig.forEach(element => { 
            let distanceInTerminus = dbres[0].distance;
            let numberofTrips = element/distanceInTerminus;
            trips.push(Math.floor(numberofTrips));
        });

        Schedule.find({id:{ $gt: 42}}).sort({id : 1})
        .exec()
        .then(docs => {

        })
        .catch(e => {
            console.log(e);
        });


        

        res.status(200).json({
            trip : trips
        });

    }).catch(e => {
        console.log(e)}
    );
});


Date.prototype.addHours = function(h) {
    this.setTime(this.getTime() + (h*60*60*1000));
    return this;
}


module.exports = router;
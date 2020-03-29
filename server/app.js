const express = require('express');
const app = express();
const morgon = require('morgan');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const scheduleSchema = require('./api/models/schedule');

const productRoutes = require('./api/routes/products')
const orderRoutes = require('./api/routes/orders')
const estimateRoutes = require('./api/routes/estimation')



mongoose.connect('mongodb://localhost:27017/sqaEstimation', {useNewUrlParser: true, useUnifiedTopology: true})
.then(dbc => {
    console.log('DB Connected');
    })
  .catch(err => {
    console.log('EXITING');
    process.exit(1);
  });


app.use(morgon('dev'));
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization');

    if(req.method === 'OPTIONS'){
        res.header('Access-Control-Allow-Methods', 'PUT, PATCH, POST, GET, DELETE');
        return res.status(200).json({});
    }
    next();
});

//Routes
app.use('/products', productRoutes);
app.use('/orders', orderRoutes);
app.use('/estimate', estimateRoutes);


app.use((req, res, next) => {
    const err = new Error('Not found');
    err.status = 404;
    next(err);
})

app.use((err, req, res, next) => {
    res.status(err.status || 500);
    res.json({
        error: {
            message: err.message
        }
    });
})

module.exports = app;
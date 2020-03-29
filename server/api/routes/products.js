const express = require('express');
const mongoose = require('mongoose');
const router = express.Router();

const Product = require('../models/schedule');


router.get('/', (req, res, next) => {
    res.status(200).json({
        message: 'Handling GET requests to /products'
    })
});

router.post('/', (req, res, next) => {
    const prod = new Product({
        _id: new mongoose.Types.ObjectId(),
        name: req.body.name,
        price: req.body.price
    });

    prod.save().then(result => {
        console.log(result);
    });
    res.status(201).json({
        message: 'Handling POST requests to /products',
        created: prod
    })
});

router.get('/:productId', (req, res, next) => {
    const id = req.params.productId;

    if(id == 'special'){
        res.status(200).json({
            message: 'Discovered the special ID',
            Id: id
        })
    }else{
        res.status(200).json({
            message: 'Not found!!!'
        })
    }
});


router.patch('/:productId', (req, res, next) => {
    res.status(200).json({
        message: "Updated Product!"
    })
});

router.delete('/:productId', (req, res, next) => {
    res.status(200).json({
        message: "Deleted product!"
    })
});

module.exports = router;
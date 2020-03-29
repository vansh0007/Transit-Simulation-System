const express = require('express');
const router = express.Router();

router.get('/', (req, res, next) => {
    res.status(200).json({
    message: 'Orders were fetched'
    });
});


router.get("/fetch").get(function(req, res) {
    console.log('fetch');
  });

router.post('/', (req, res, next) => {
    const order = {
        quantity: req.body.quantity,
        productID: req.body.productID
    };
    res.status(201).json({
    message: 'Orders was fetched',
    order: order
    });
});



router.get('/:orderId', (req, res, next) => {
    res.status(200).json({
    message: 'Orders Details',
    id: req.params.orderId
    });
});

router.delete('/:orderId', (req, res, next) => {
    res.status(200).json({
    message: 'Orders Deleted',
    id: req.params.orderId
    });
});


module.exports = router;
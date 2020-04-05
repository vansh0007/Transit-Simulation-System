const mongoose = require('mongoose');

const configSchema = mongoose.Schema({
    _id: mongoose.Types.ObjectId,
    distance : Number,
});

module.exports = mongoose.model('Configurations', configSchema);
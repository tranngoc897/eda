const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    username: String,
    email: {
        type: String,
        required: true,
        set: function (value) {
            return value.trim().toLowerCase()
        },
        validate: [
            function (email) {
                return (email.match(/[a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/i) != null)
            }, 'Invalid email'
        ]
    },
    password: String

});

module.exports = mongoose.model('user', userSchema);
const  express = require('express');
const  router = express.Router();
const user_controller = require('../controllers/userController');

router.get('/:userId', user_controller.getUser);
router.post('/', user_controller.createUser);
router.get('/', user_controller.getUsers);
router.put('/:userId', user_controller.updateUser);
router.delete('/:userId', user_controller.deleteUser);

module.exports = router;

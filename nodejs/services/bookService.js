/*
const httpStatus = require('http-status');
const UserModel = require('../models/user');

const getUserById = async (userId) => {
  const user = await UserModel.findById({ _id: userId });
  return user;
};

const createUser = async (userBody) => {
  /!*
  if (await User.isEmailTaken(userBody.email)) {
    //throw new ApiError(httpStatus.BAD_REQUEST, 'Email already taken');
  }
  *!/
  const user = await UserModel.create(userBody);
  return user;

};

const queryUsers = async (filter, options) => {
  const users = await userModel.find({}).lean();
  //const users = await User.paginate(filter, options);
  return users;
};

/!**
 * Get user by email
 * @param {string} email
 * @returns {Promise<User>}
 *!/
const getUserByEmail = async (email) => {
  return User.findOne({ email });
};

/!**
 * Update user by id
 * @param {ObjectId} userId
 * @param {Object} updateBody
 * @returns {Promise<User>}
 *!/
const updateUserById = async (userId, updateBody) => {
  const user = await getUserById(userId);
  if (!user) {
    throw new ApiError(httpStatus.NOT_FOUND, 'User not found');
  }
  if (updateBody.email && (await User.isEmailTaken(updateBody.email, userId))) {
    throw new ApiError(httpStatus.BAD_REQUEST, 'Email already taken');
  }
  Object.assign(user, updateBody);
  await user.save();
  return user;
};

/!**
 * Delete user by id
 * @param {ObjectId} userId
 * @returns {Promise<User>}
 *!/
const deleteUserById = async (userId) => {
  /!*
  const { userId } = req.params;
  await userModel.deleteOne({ _id: userId });
  res.status(204).send();
  *!/
  const user = await getUserById(userId);
  if (!user) {
    throw new ApiError(httpStatus.NOT_FOUND, 'User not found');
  }
  await user.remove();
  return user;
};

module.exports = {
  createUser,
  queryUsers,
  getUserById,
  getUserByEmail,
  updateUserById,
  deleteUserById,
};

*/

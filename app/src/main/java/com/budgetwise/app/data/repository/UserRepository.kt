package com.budgetwise.app.data.repository

import com.budgetwise.app.data.db.CategoryDao
import com.budgetwise.app.data.db.UserDao
import com.budgetwise.app.data.entities.Category
import com.budgetwise.app.data.entities.User
import com.budgetwise.app.utils.hashPassword

class UserRepository(
    private val userDao: UserDao,
    private val categoryDao: CategoryDao
) {
    // Returns new user ID, -1 if username taken, -2 if email taken
    suspend fun register(username: String, email: String, password: String): Long {
        if (userDao.usernameExists(username) > 0) return -1L
        if (userDao.emailExists(email) > 0) return -2L
        val user = User(
            username = username,
            email = email,
            passwordHash = hashPassword(password)
        )
        val newId = userDao.insertUser(user)
        seedDefaultCategories(newId.toInt())
        return newId
    }

    suspend fun login(identifier: String, password: String): User? {
        val user = userDao.getUserByIdentifier(identifier) ?: return null
        return if (user.passwordHash == hashPassword(password)) user else null
    }

    private suspend fun seedDefaultCategories(userId: Int) {
        val defaults = listOf(
            Category(userId = userId, name = "Uncategorized",  iconName = "ic_help",         colour = "#9E9E9E", isDefault = true),
            Category(userId = userId, name = "Groceries",      iconName = "ic_shopping_cart", colour = "#4CAF50"),
            Category(userId = userId, name = "Dining Out",     iconName = "ic_restaurant",    colour = "#FF9800"),
            Category(userId = userId, name = "Transport",      iconName = "ic_directions_car",colour = "#2196F3"),
            Category(userId = userId, name = "Entertainment",  iconName = "ic_movie",         colour = "#9C27B0"),
            Category(userId = userId, name = "Utilities",      iconName = "ic_bolt",          colour = "#F44336"),
            Category(userId = userId, name = "Rent/Mortgage",  iconName = "ic_home",          colour = "#795548"),
            Category(userId = userId, name = "Shopping",       iconName = "ic_shopping_bag",  colour = "#E91E63")
        )
        defaults.forEach { categoryDao.insertCategory(it) }
    }
}

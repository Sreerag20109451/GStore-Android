import com.example.gstore_android.data.models.Category
import com.example.gstore_android.data.models.Product



val defaultUrl : String = "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2Fdefault.jpg?alt=media&token=c3b32558-96c9-46f0-a69b-91ae465be36c"

val products = listOf<Product>(
Product(
name = "Apple",
category = Category.Fruits,
price = 2.99,
discount = 5.0,
quantity = 50.0,
imageUrl = "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2FFruits%2Fapple.jpg?alt=media&token=4a2120b7-efae-4ff2-841a-a0b8fec040ab"
),
Product(
name = "Banana",
category = Category.Fruits,
price = 1.29,
discount = 10.0,
quantity = 100.0,
imageUrl = "https://firebasestorage.googleapis.com/v0/b/gstore-final.firebasestorage.app/o/Products%2FFruits%2Fbanana.jpg?alt=media&token=010b6e9b-17ba-40ea-a913-723d72bef363"
),
Product(
name = "Mango",
category = Category.Fruits,
price = 3.49,
discount = 15.0,
quantity = 30.0,
imageUrl = defaultUrl
),
// Vegetables
Product(
name = "Carrot",
category = Category.Vegetables,
price = 1.49,
discount = 5.0,
quantity = 80.0,
imageUrl = defaultUrl
),
Product(
name = "Broccoli",
category = Category.Vegetables,
price = 2.79,
discount = 8.0,
quantity = 60.0,
imageUrl = defaultUrl
),
Product(
name = "Tomato",
category = Category.Vegetables,
price = 1.99,
discount = 12.0,
quantity = 90.0,
imageUrl = defaultUrl
),
// Dairy
Product(
name = "Whole Milk",
category = Category.Dairy,
price = 3.59,
discount = 5.0,
quantity = 40.0,
imageUrl = defaultUrl
),
Product(
name = "Cheddar Cheese",
category = Category.Dairy,
price = 4.99,
discount = 10.0,
quantity = 25.0,
imageUrl = defaultUrl
),
Product(
name = "Butter",
category = Category.Dairy,
price = 2.49,
discount = 7.0,
quantity = 35.0,
imageUrl = defaultUrl
),
// Cereals
Product(
name = "Oats",
category = Category.Cereals,
price = 3.99,
discount = 5.0,
quantity = 50.0,
imageUrl = defaultUrl
),
Product(
name = "Cornflakes",
category = Category.Cereals,
price = 2.99,
discount = 8.0,
quantity = 60.0,
imageUrl = defaultUrl
),
Product(
name = "Muesli",
category = Category.Cereals,
price = 5.49,
discount = 10.0,
quantity = 40.0,
imageUrl = defaultUrl
)
)

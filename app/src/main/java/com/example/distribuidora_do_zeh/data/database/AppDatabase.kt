package com.example.distribuidora_do_zeh.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.distribuidora_do_zeh.data.dao.BebidaDao
import com.example.distribuidora_do_zeh.data.dao.CategoriaDao
import com.example.distribuidora_do_zeh.data.dao.MovimentacaoDao
import com.example.distribuidora_do_zeh.data.entity.Bebida
import com.example.distribuidora_do_zeh.data.entity.Categoria
import com.example.distribuidora_do_zeh.data.entity.Movimentacao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Categoria::class, Bebida::class, Movimentacao::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun bebidaDao(): BebidaDao
    abstract fun movimentacaoDao(): MovimentacaoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "distribuidora_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(database: AppDatabase) {
            val categoriaDao = database.categoriaDao()
            val bebidaDao = database.bebidaDao()

            // Categorias iniciais
            val categoriasCervejas = Categoria(nome = "Cerveja")
            val categoriasRefrigerantes = Categoria(nome = "Refrigerante")
            val categoriasSucos = Categoria(nome = "Suco")
            val categoriasAguas = Categoria(nome = "Água")
            val categoriasEnergeticos = Categoria(nome = "Energético")
            val categoriaVodka = Categoria(nome = "Vodka")
            val categoriaWhisky = Categoria(nome = "Whisky")
            val categoriaCachaca = Categoria(nome = "Cachaça")
            val categoriaVinho = Categoria(nome = "Vinho")
            val categoriaGin = Categoria(nome = "Gin")
            val categoriaRum = Categoria(nome = "Rum")
            val categoriaTequila = Categoria(nome = "Tequila")
            val categoriaEspumante = Categoria(nome = "Espumante")
            val categoriaOutros = Categoria(nome = "Outros")


            val idCerveja = categoriaDao.insertCategoria(categoriasCervejas).toInt()
            val idRefrigerante = categoriaDao.insertCategoria(categoriasRefrigerantes).toInt()
            val idSuco = categoriaDao.insertCategoria(categoriasSucos).toInt()
            val idAgua = categoriaDao.insertCategoria(categoriasAguas).toInt()
            val idEnergetico = categoriaDao.insertCategoria(categoriasEnergeticos).toInt()
            val idVodka = categoriaDao.insertCategoria(categoriaVodka).toInt()
            val idWhisky = categoriaDao.insertCategoria(categoriaWhisky).toInt()
            val idCachaca = categoriaDao.insertCategoria(categoriaCachaca).toInt()
            val idVinho = categoriaDao.insertCategoria(categoriaVinho).toInt()
            val idGin = categoriaDao.insertCategoria(categoriaGin).toInt()
            val idRum = categoriaDao.insertCategoria(categoriaRum).toInt()
            val idTequila = categoriaDao.insertCategoria(categoriaTequila).toInt()
            val idEspumante = categoriaDao.insertCategoria(categoriaEspumante).toInt()
            val idOutros = categoriaDao.insertCategoria(categoriaOutros).toInt()


            // Bebidas iniciais
            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idCerveja,
                    nome = "Skol Pilsen",
                    volume = "350ml",
                    quantidadeEstoque = 50,
                    precoCompra = 2.50,
                    precoVenda = 4.00
                )
            )

            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idCerveja,
                    nome = "Brahma Duplo Malte",
                    volume = "350ml",
                    quantidadeEstoque = 30,
                    precoCompra = 3.00,
                    precoVenda = 4.50
                )
            )

            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idRefrigerante,
                    nome = "Coca-Cola",
                    volume = "2L",
                    quantidadeEstoque = 40,
                    precoCompra = 5.00,
                    precoVenda = 8.00
                )
            )

            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idRefrigerante,
                    nome = "Guaraná Antarctica",
                    volume = "2L",
                    quantidadeEstoque = 35,
                    precoCompra = 4.50,
                    precoVenda = 7.50
                )
            )

            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idSuco,
                    nome = "Del Valle Laranja",
                    volume = "1L",
                    quantidadeEstoque = 25,
                    precoCompra = 4.00,
                    precoVenda = 6.50
                )
            )

            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idAgua,
                    nome = "Água Crystal",
                    volume = "500ml",
                    quantidadeEstoque = 100,
                    precoCompra = 1.00,
                    precoVenda = 2.00
                )
            )

            bebidaDao.insertBebida(
                Bebida(
                    categoriaId = idEnergetico,
                    nome = "Red Bull",
                    volume = "250ml",
                    quantidadeEstoque = 20,
                    precoCompra = 6.00,
                    precoVenda = 10.00
                )
            )

            val bebidasAlcoolicas = listOf(
                Bebida(
                    categoriaId = idVodka,
                    nome = "Smirnoff",
                    volume = "1L",
                    quantidadeEstoque = 20,
                    precoCompra = 22.0,
                    precoVenda = 35.0
                ),
                Bebida(
                    categoriaId = idVodka,
                    nome = "Absolut",
                    volume = "750ml",
                    quantidadeEstoque = 10,
                    precoCompra = 55.0,
                    precoVenda = 90.0
                ),
                Bebida(
                    categoriaId = idWhisky,
                    nome = "Johnnie Walker Red Label",
                    volume = "1L",
                    quantidadeEstoque = 8,
                    precoCompra = 75.0,
                    precoVenda = 115.0
                ),
                Bebida(
                    categoriaId = idWhisky,
                    nome = "Jack Daniel's",
                    volume = "1L",
                    quantidadeEstoque = 5,
                    precoCompra = 110.0,
                    precoVenda = 160.0
                ),
                Bebida(
                    categoriaId = idCachaca,
                    nome = "51",
                    volume = "1L",
                    quantidadeEstoque = 30,
                    precoCompra = 7.0,
                    precoVenda = 15.0
                ),
                Bebida(
                    categoriaId = idCachaca,
                    nome = "Velho Barreiro",
                    volume = "910ml",
                    quantidadeEstoque = 18,
                    precoCompra = 8.0,
                    precoVenda = 14.0
                ),
                Bebida(
                    categoriaId = idVinho,
                    nome = "Vinho Tinto Seco",
                    volume = "750ml",
                    quantidadeEstoque = 12,
                    precoCompra = 20.0,
                    precoVenda = 45.0
                ),
                Bebida(
                    categoriaId = idVinho,
                    nome = "Vinho Branco Suave",
                    volume = "750ml",
                    quantidadeEstoque = 10,
                    precoCompra = 18.0,
                    precoVenda = 40.0
                ),
                Bebida(
                    categoriaId = idGin,
                    nome = "Tanqueray",
                    volume = "750ml",
                    quantidadeEstoque = 7,
                    precoCompra = 95.0,
                    precoVenda = 150.0
                ),
                Bebida(
                    categoriaId = idTequila,
                    nome = "Jose Cuervo Silver",
                    volume = "750ml",
                    quantidadeEstoque = 6,
                    precoCompra = 80.0,
                    precoVenda = 125.0
                ),
                Bebida(
                    categoriaId = idEspumante,
                    nome = "Chandon Brut",
                    volume = "750ml",
                    quantidadeEstoque = 4,
                    precoCompra = 65.0,
                    precoVenda = 110.0
                )
            )

            bebidasAlcoolicas.forEach { bebida ->
                bebidaDao.insertBebida(bebida)
            }

        }
    }
}

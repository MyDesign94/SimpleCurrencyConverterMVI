package ru.isaevSV.currencyconverter.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.isaevSV.currencyconverter.core.Constant.BASE_URL
import ru.isaevSV.currencyconverter.core.Constant.BASE_URL_XML
import ru.isaevSV.currencyconverter.data.data_source.CurrencyDatabase
import ru.isaevSV.currencyconverter.data.data_source.CurrencyDatabaseRepositoryImpl
import ru.isaevSV.currencyconverter.data.remote.json.CentralBankRussiaApi
import ru.isaevSV.currencyconverter.data.remote.xml.CentralBankRussiaApiXML
import ru.isaevSV.currencyconverter.data.repository.json.CentralBankRussiaRepositoryImpl
import ru.isaevSV.currencyconverter.data.repository.xml.CentralBankRussiaRepositoryXMLImpl
import ru.isaevSV.currencyconverter.domain.repository.data_source.CurrencyDatabaseRepository
import ru.isaevSV.currencyconverter.domain.repository.remote.json.CentralBankRussiaRepository
import ru.isaevSV.currencyconverter.domain.repository.remote.xml.CentralBankRussiaRepositoryXML
import ru.isaevSV.currencyconverter.domain.use_case.converter.ConverterUseCase
import ru.isaevSV.currencyconverter.domain.use_case.data_source.DataSourceUseCase
import ru.isaevSV.currencyconverter.domain.use_case.data_source.GetCurrencyDataUseCase
import ru.isaevSV.currencyconverter.domain.use_case.data_source.InsertCurrencyDataUseCase
import ru.isaevSV.currencyconverter.domain.use_case.remote.json.GetAllCurrencyUseCase
import ru.isaevSV.currencyconverter.domain.use_case.remote.xml.GetAllCurrencyXMLUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: Application): CurrencyDatabase {
        return Room.databaseBuilder(
            app,
            CurrencyDatabase::class.java,
            CurrencyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDatabaseRepository(db: CurrencyDatabase): CurrencyDatabaseRepository {
        return CurrencyDatabaseRepositoryImpl(dao = db.currencyDao)
    }

    @Provides
    @Singleton
    fun provideDataSourceUseCase(repository: CurrencyDatabaseRepository): DataSourceUseCase {
        return DataSourceUseCase(
            getCurrency = GetCurrencyDataUseCase(repository = repository),
            insertCurrency = InsertCurrencyDataUseCase(repository = repository)
        )
    }

    @Provides
    @Singleton
    fun provideCentralBankRussiaApiXML(): CentralBankRussiaApiXML {
        return Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl(BASE_URL_XML)
            .build()
            .create(CentralBankRussiaApiXML::class.java)
    }

    @Provides
    @Singleton
    fun provideCentralBankRussiaRepositoryXML(api: CentralBankRussiaApiXML): CentralBankRussiaRepositoryXML {
        return CentralBankRussiaRepositoryXMLImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideRemoteXMLUseCase(repository: CentralBankRussiaRepositoryXML): GetAllCurrencyXMLUseCase {
        return GetAllCurrencyXMLUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideCentralBankRussiaApi(): CentralBankRussiaApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CentralBankRussiaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCentralBankRussiaRepository(api: CentralBankRussiaApi): CentralBankRussiaRepository {
        return CentralBankRussiaRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideRemoteUseCase(repository: CentralBankRussiaRepository): GetAllCurrencyUseCase {
        return GetAllCurrencyUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideConvector(): ConverterUseCase {
        return ConverterUseCase()
    }
}

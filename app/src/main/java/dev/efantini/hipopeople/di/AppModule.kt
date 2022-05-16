package dev.efantini.hipopeople.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.efantini.hipopeople.data.local.HipoPeopleDatabase
import dev.efantini.hipopeople.data.local.repository.MemberRepositoryLocal
import dev.efantini.hipopeople.data.remote.GithubApi
import dev.efantini.hipopeople.data.remote.repository.GithubUserRepositoryRemote
import dev.efantini.hipopeople.domain.repository.GithubUserRepository
import dev.efantini.hipopeople.domain.repository.MemberRepository
import dev.efantini.hipopeople.domain.use_case.AddMemberUseCase
import dev.efantini.hipopeople.domain.use_case.GetGithubUserUseCase
import dev.efantini.hipopeople.domain.use_case.GetMembersUseCase
import dev.efantini.hipopeople.shared.Constants
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.GITHUB_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): HipoPeopleDatabase {
        return Room.databaseBuilder(
            app,
            HipoPeopleDatabase::class.java,
            HipoPeopleDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMemberRepository(db: HipoPeopleDatabase): MemberRepository {
        return MemberRepositoryLocal(db.memberDao)
    }

    @Provides
    @Singleton
    fun provideGithubUserRepository(api: GithubApi): GithubUserRepository {
        return GithubUserRepositoryRemote(api)
    }

    @Provides
    @Singleton
    fun provideGetMembersUseCase(repository: MemberRepository): GetMembersUseCase {
        return GetMembersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddMemberUseCase(repository: MemberRepository): AddMemberUseCase {
        return AddMemberUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetGithubUserUseCase(repository: GithubUserRepository): GetGithubUserUseCase {
        return GetGithubUserUseCase(repository)
    }
}

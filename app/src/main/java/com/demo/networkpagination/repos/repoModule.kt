package com.demo.networkpagination.repos

import com.demo.networkpagination.repos.communityRepo.CommunityRepo
import org.koin.dsl.module

val repoModule = module {
    single { CommunityRepo(get()) }
}
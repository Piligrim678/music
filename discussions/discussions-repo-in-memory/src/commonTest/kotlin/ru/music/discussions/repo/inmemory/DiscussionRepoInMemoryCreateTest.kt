package ru.music.discussions.repo.inmemory

import ru.music.discussions.repo.tests.RepoDiscussionCreateTest


class DiscussionRepoInMemoryCreateTest : RepoDiscussionCreateTest() {
    override val repo = DiscussionsRepoInMemory(
        initObjects = initObjects,
        randomUuid = { lockNew.asString() }
    )
}
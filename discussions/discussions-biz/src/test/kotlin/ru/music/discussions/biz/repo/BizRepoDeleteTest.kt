package ru.music.discussions.biz.repo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import permissions.MusicPrincipalModel
import permissions.MusicUserGroups
import repo.DbDiscussionResponse
import ru.music.common.DiscContext
import ru.music.common.DiscCorSettings
import ru.music.common.models.*
import ru.music.discussions.biz.DiscussionsProcessor
import ru.music.discussions.repo.tests.DiscussionsRepositoryMock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BizRepoDeleteTest {

    private val userId = DiscUserId("321")
    private val command = DiscCommand.DELETE

    private val initDisc = DiscDiscussion(
        id = DiscId("12345"),
        title = "abc",
        soundUrl = "abc",
        status = DiscStatus.OPEN,
        answers = mutableListOf(DiscAnswer("111")),
        ownerId = userId,
        lock = DiscLock("123-234-abc-ABC")
    )

    private val repo by lazy {
        DiscussionsRepositoryMock(
            invokeReadDiscussion = {
                DbDiscussionResponse(
                    isSuccess = true,
                    data = initDisc
                )
            },
            invokeDeleteDiscussion = {
                if (it.id == initDisc.id)
                    DbDiscussionResponse(
                        isSuccess = true,
                        data = initDisc
                    )
                else DbDiscussionResponse(isSuccess = false, data = null)
            }
        )
    }

    private val settings = DiscCorSettings(
        repoTest = repo
    )
    private val processor = DiscussionsProcessor(settings)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun repoDeleteSuccessTest() = runTest {
        val discToDel = DiscDiscussion(
            id = DiscId("12345"),
            lock = DiscLock("123-234-abc-ABC")
        )
        val ctx = DiscContext(
            command = command,
            state = DiscState.NONE,
            workMode = DiscWorkMode.TEST,
            discussionRequest = discToDel,
            principal = MusicPrincipalModel(
                id = userId,
                groups = setOf(
                    MusicUserGroups.USER,
                    MusicUserGroups.TEST,
                )
            ),
        )
        processor.exec(ctx)
        assertEquals(DiscState.FINISHING, ctx.state)
        assertTrue { ctx.errors.isEmpty() }
        assertEquals(initDisc.id, ctx.discussionResponse.id)
        assertEquals(initDisc.title, ctx.discussionResponse.title)
        assertEquals(initDisc.soundUrl, ctx.discussionResponse.soundUrl)
        assertEquals(initDisc.status, ctx.discussionResponse.status)
        assertEquals(1, ctx.discussionResponse.answers.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun repoDeleteNotFoundTest() = repoNotFoundTest(command)
}

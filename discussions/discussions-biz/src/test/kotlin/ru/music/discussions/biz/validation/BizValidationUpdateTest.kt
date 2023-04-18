package ru.music.discussions.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.music.common.models.DiscCommand
import ru.music.discussions.biz.DiscussionsProcessor
import ru.music.discussions.biz.validation.*
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BizValidationUpdateTest {

    private val command = DiscCommand.UPDATE
    private val processor by lazy { DiscussionsProcessor() }

    @Test fun correctTitle() = validationTitleCorrect(command, processor)
    @Test fun trimTitle() = validationTitleTrim(command, processor)
    @Test fun emptyTitle() = validationTitleEmpty(command, processor)
    @Test fun badSymbolsTitle() = validationTitleSymbols(command, processor)

    @Test fun correctSoundUrl() = validationSoundUrlCorrect(command, processor)
    @Test fun trimSoundUrl() = validationSoundUrlTrim(command, processor)
    @Test fun emptySoundUrl() = validationSoundUrlEmpty(command, processor)
    @Test fun badSymbolsSoundUrl() = validationSoundUrlSymbols(command, processor)

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validationIdTrim(command, processor)
    @Test fun emptyId() = validationIdEmpty(command, processor)
    @Test fun badFormatId() = validationIdFormat(command, processor)


}


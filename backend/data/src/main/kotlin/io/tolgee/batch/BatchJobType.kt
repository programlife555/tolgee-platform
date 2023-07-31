package io.tolgee.batch

import io.tolgee.activity.data.ActivityType
import io.tolgee.batch.processors.ClearTranslationsChunkProcessor
import io.tolgee.batch.processors.CopyTranslationsChunkProcessor
import io.tolgee.batch.processors.DeleteKeysChunkProcessor
import io.tolgee.batch.processors.MachineTranslationChunkProcessor
import io.tolgee.batch.processors.PreTranslationByTmChunkProcessor
import io.tolgee.batch.processors.SetKeysNamespaceChunkProcessor
import io.tolgee.batch.processors.SetTranslationsStateChunkProcessor
import io.tolgee.batch.processors.TagKeysChunkProcessor
import io.tolgee.batch.processors.UntagKeysChunkProcessor
import kotlin.reflect.KClass

enum class BatchJobType(
  val activityType: ActivityType,
  /**
   * 0 means no chunking
   */
  val maxRetries: Int,
  val processor: KClass<out ChunkProcessor<*, *, *>>,
  val defaultRetryWaitTimeInMs: Int = 2000,
) {
  PRE_TRANSLATE_BY_MT(
    activityType = ActivityType.BATCH_PRE_TRANSLATE_BY_MT,
    maxRetries = 3,
    processor = PreTranslationByTmChunkProcessor::class,
  ),
  MACHINE_TRANSLATE(
    activityType = ActivityType.BATCH_MACHINE_TRANSLATE,
    maxRetries = 3,
    processor = MachineTranslationChunkProcessor::class,
  ),
  DELETE_KEYS(
    activityType = ActivityType.KEY_DELETE,
    maxRetries = 3,
    processor = DeleteKeysChunkProcessor::class,
  ),
  SET_TRANSLATIONS_STATE(
    activityType = ActivityType.BATCH_SET_TRANSLATION_STATE,
    maxRetries = 3,
    processor = SetTranslationsStateChunkProcessor::class,
  ),
  CLEAR_TRANSLATIONS(
    activityType = ActivityType.BATCH_CLEAR_TRANSLATIONS,
    maxRetries = 3,
    processor = ClearTranslationsChunkProcessor::class,
  ),
  COPY_TRANSLATIONS(
    activityType = ActivityType.BATCH_COPY_TRANSLATIONS,
    maxRetries = 3,
    processor = CopyTranslationsChunkProcessor::class,
  ),
  TAG_KEYS(
    activityType = ActivityType.BATCH_TAG_KEYS,
    maxRetries = 3,
    processor = TagKeysChunkProcessor::class,
  ),
  UNTAG_KEYS(
    activityType = ActivityType.BATCH_UNTAG_KEYS,
    maxRetries = 3,
    processor = UntagKeysChunkProcessor::class,
  ),
  SET_KEYS_NAMESPACE(
    activityType = ActivityType.BATCH_SET_KEYS_NAMESPACE,
    maxRetries = 3,
    processor = SetKeysNamespaceChunkProcessor::class,
  );
}

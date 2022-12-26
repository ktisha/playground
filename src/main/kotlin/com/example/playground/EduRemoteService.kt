package com.example.playground

import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.wm.ToolWindowId
import com.intellij.openapi.wm.ToolWindowManager
import com.jetbrains.codeWithMe.model.projectViewModel
import com.jetbrains.rd.platform.util.idea.LifetimedService
import com.jetbrains.rdserver.core.RemoteProjectSession

class EduRemoteService(private val session: RemoteProjectSession) : LifetimedService() {
  init {
    LOG.info("EduRemoteService.init")
    val project = session.project
    val scheduler = session.protocol.scheduler
    scheduler.queue {
      LOG.info("scheduler.queue")
      val model = session.protocol.projectViewModel
      model.activate.fire("PackagesPane")
    }
  }

  companion object {
    fun getInstance(session: RemoteProjectSession): EduRemoteService =
      session.getService(EduRemoteService::class.java)

    private val LOG = logger<EduRemoteService>()
  }

}

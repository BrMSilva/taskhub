package com.seunome.taskhub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskhubApplication

fun main(args: Array<String>) {
	runApplication<TaskhubApplication>(*args)
}

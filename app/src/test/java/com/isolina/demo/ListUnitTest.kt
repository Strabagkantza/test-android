package com.isolina.demo

import com.isolina.demo.usecases.UseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ListUnitTest{
    private lateinit var useCaseMock: UseCase

    @Before
    fun setup(){
        useCaseMock = mock(UseCase::class.java)
    }

    @Test
    fun checkListIsNotNullTest(){
        runBlocking {
            `when`(useCaseMock.executeBeers()).thenReturn(getResponse())
            val result = useCaseMock.executeBeers()
            assertNotNull(result.data)
            val sizeList = result.data?.isNotEmpty() ?: false
            assertTrue(sizeList)
            val name = result.data?.get(0)?.name ?: ""
            assertEquals("Buzz", name)
        }
    }

}
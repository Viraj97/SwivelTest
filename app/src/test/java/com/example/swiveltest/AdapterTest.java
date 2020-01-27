package com.example.swiveltest;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.swiveltest.CardModel.CardModel;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AdapterTest {
    @Test
    public void testGetSize() {
        List<CardModel> data = null;
        Context context = null;
        LayoutInflater layoutInflater = mock(LayoutInflater.class);
        Adapter adapter = new Adapter(context, data);
        assertEquals(adapter.getItemCount(), NullPointerException.class);
    }
}

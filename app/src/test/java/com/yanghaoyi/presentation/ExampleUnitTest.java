package com.yanghaoyi.presentation;

import org.junit.Test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("hello world");
                

            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                return Integer.valueOf(s);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Throwable {
                return Observable.just(String.valueOf(integer));
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Throwable {
                System.out.println(integer);
            }
        });


        Observable.zip(Observable.just(1, 3, 5), Observable.just("fd", "fds", "aaaa"), new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {

            }
        });
    }
}
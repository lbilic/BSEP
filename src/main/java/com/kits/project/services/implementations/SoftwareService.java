package com.kits.project.services.implementations;

import com.kits.project.model.Software;
import com.kits.project.repositories.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SoftwareService implements com.kits.project.services.interfaces.SoftwareService {
    @Autowired
    private SoftwareRepository softwareRepository;

    @Override
    public List<Software> getConnectedSoftware(String nameId) {
//        Software software = this.softwareRepository.getByNameId(nameId);
//        return software.getConnectedSoftware();
        return new List<Software>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Software> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Software software) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Software> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Software> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Software get(int index) {
                return null;
            }

            @Override
            public Software set(int index, Software element) {
                return null;
            }

            @Override
            public void add(int index, Software element) {

            }

            @Override
            public Software remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Software> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Software> listIterator(int index) {
                return null;
            }

            @Override
            public List<Software> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }
}

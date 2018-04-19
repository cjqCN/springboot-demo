package com.example.demo.util.id;

public interface ID {

    String id();

    long idForLong();


    static ID newID(final String id) {
        return new ID() {
            @Override
            public String id() {
                return id;
            }

            @Override
            public long idForLong() {
                return Long.valueOf(id());
            }

        };
    }

    static ID newID(final long id) {
        return new ID() {
            @Override
            public long idForLong() {
                return id;
            }

            @Override
            public String id() {
                return String.valueOf(idForLong());
            }
        };
    }

}

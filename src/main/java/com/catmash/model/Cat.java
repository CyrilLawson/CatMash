package com.catmash.model;

import jakarta.validation.constraints.NotNull;

public record Cat (@NotNull String id, @NotNull String url, int score) {
}

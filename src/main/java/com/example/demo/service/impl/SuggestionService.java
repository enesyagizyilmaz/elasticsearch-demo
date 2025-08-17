package com.example.demo.service.impl;

import com.example.demo.dto.request.SuggestionDTO;
import com.example.demo.service.ISuggestionService;
import com.example.demo.util.Constants;
import com.example.demo.util.NativeQueryBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.suggest.response.Suggest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService implements ISuggestionService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SuggestionService.class);

    private final ElasticsearchOperations elasticsearchOperations;

    public SuggestionService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     * @param parameters
     * @return
     */
    public List<String> fetchSuggestions(SuggestionDTO parameters) {
        log.info("suggestion request: {}", parameters);
        // 1. Kullanıcıdan gelen parametrelerle bir ElasticSearch suggest query oluşturuluyor.
        NativeQuery query = NativeQueryBuilder.toSuggestQuery(parameters);
        // 2. Bu query Elasticsearch'e gönderiliyor ve sonuçlar searchHits içinde dönüyor.
        SearchHits<Object> searchHits = this.elasticsearchOperations.search(query, Object.class, Constants.Index.SUGGESTION);
        // 3. searchHits içindeki "suggest" kısmı (ElasticSearch suggest API’den dönen kısım) alınıyor.
        return Optional.ofNullable(searchHits.getSuggest())
                // 4. İlgili suggestion adı (Constants.Suggestion.SUGGEST_NAME) ile suggestion elde ediliyor.
                .map(s -> s.getSuggestion(Constants.Suggestion.SUGGEST_NAME))
                .stream()
                // 5. Suggestion içindeki entry’ler alınıyor.
                .map(Suggest.Suggestion::getEntries)
                .flatMap(Collection::stream)
                // 6. Entry’lerin içindeki opsiyonlar (yani önerilen kelimeler/cümleler) alınıyor.
                .map(Suggest.Suggestion.Entry::getOptions)
                .flatMap(Collection::stream)
                // 7. Opsiyonların text alanları (öneri metinleri) çıkarılıyor.
                .map(Suggest.Suggestion.Entry.Option::getText)
                // 8. Bütün öneri metinleri bir listeye toplanıyor.
                .toList();
    }

}

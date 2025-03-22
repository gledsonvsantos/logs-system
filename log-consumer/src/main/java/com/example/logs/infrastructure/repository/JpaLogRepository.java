package com.example.logs.infrastructure.repository;

import com.example.logs.domain.Log;
import com.example.logs.domain.LogRepository;
import com.example.logs.infrastructure.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Repository
public class JpaLogRepository implements LogRepository {

    private final SpringDataLogRepository springDataLogRepository;
    private final LogMapper logMapper;

    /**
     * Persiste um log no banco de dados.
     *
     * @param logDomain Objeto do domínio a ser persistido.
     */
    @Override
    public void save(Log logDomain) {
        log.debug("Iniciando a persistência do log: {}", logDomain);

        try {
            // Converte o objeto de domínio para uma entidade do banco
            LogEntity logEntity = logMapper.toEntity(logDomain);
            springDataLogRepository.save(logEntity);

            log.info("Log persistido com sucesso. Mensagem: {}", logDomain.getMessage());
        } catch (DataAccessException e) {
            // Log detalhado e lançamento de exceção customizada
            log.error("Erro ao persistir o log no banco de dados: {}", logDomain, e);
            throw new DatabaseOperationException("Erro ao salvar o log no banco de dados.", e);
        }
    }

    /**
     * Retorna todos os logs persistidos no banco de dados.
     *
     * @return Lista de logs do domínio.
     */
    @Override
    public List<Log> findAll() {
        log.debug("Iniciando busca de todos os logs no banco de dados.");

        try {
            // Busca todas as entidades do banco de dados
            List<LogEntity> entities = springDataLogRepository.findAll();

            log.info("Busca concluída. Total de logs encontrados: {}", entities.size());

            // Mapeia as entidades do banco para objetos do domínio
            return entities.stream()
                    .map(logMapper::toDomain)
                    .toList();
        } catch (DataAccessException e) {
            log.error("Erro ao buscar logs no banco de dados.", e);
            throw new DatabaseOperationException("Erro ao buscar logs no banco de dados.", e);
        }
    }
}
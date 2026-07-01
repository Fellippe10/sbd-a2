package com.xptotec.reservas.config;
import com.xptotec.reservas.adapter.in.rest.mapper.ReservaRestMapper;
import com.xptotec.reservas.adapter.out.persistence.mapper.ReservaJpaMapper;
import com.xptotec.reservas.application.ReservaApplicationService;
import com.xptotec.reservas.domain.port.out.ClienteRepository;
import com.xptotec.reservas.domain.port.out.ProfissionalRepository;
import com.xptotec.reservas.domain.port.out.ReservaRepository;
import com.xptotec.reservas.domain.port.out.ServicoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BeanConfiguration {
    @Bean
    public ReservaApplicationService reservaApplicationService(
            ReservaRepository reservaRepository,
            ClienteRepository clienteRepository,
            ProfissionalRepository profissionalRepository,
            ServicoRepository servicoRepository) {
        return new ReservaApplicationService(
                reservaRepository,
                clienteRepository,
                profissionalRepository,
                servicoRepository
        );
    }
    @Bean
    public ReservaJpaMapper reservaJpaMapper() {
        return new ReservaJpaMapper();
    }
    @Bean
    public ReservaRestMapper reservaRestMapper() {
        return new ReservaRestMapper();
    }
}
